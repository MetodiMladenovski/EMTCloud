package mk.ukim.finki.usermanagment.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.events.storage.DecreaseBucketEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.IncreaseBucketEvent;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import mk.ukim.finki.usermanagment.domain.exceptions.FileFailedToStore;
import mk.ukim.finki.usermanagment.domain.exceptions.FileNotFoundException;
import mk.ukim.finki.usermanagment.domain.model.FileEntity;
import mk.ukim.finki.usermanagment.domain.model.FileId;
import mk.ukim.finki.usermanagment.domain.repository.FileRepository;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import mk.ukim.finki.usermanagment.service.FileService;
import mk.ukim.finki.usermanagment.service.form.FileForm;
import mk.ukim.finki.usermanagment.service.form.FileResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final DomainEventPublisher eventPublisher;



    @Override
    public FileResponse store(FileForm fileForm){
        MultipartFile multipartFile = fileForm.getMultipartFile();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        ObjectSize fileSize = new ObjectSize(multipartFile.getSize());

        try {
            FileEntity fileEntity = new FileEntity(fileName, fileForm.getMultipartFile().getContentType(), fileSize, multipartFile.getBytes(), fileForm.getBucketId());
            FileEntity savedFile = fileRepository.saveAndFlush(fileEntity);
            eventPublisher.publish(new IncreaseBucketEvent(fileForm.getBucketId().getId(), fileSize.getSize()));

            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/buckets/download/")
                    .path(savedFile.getId().getId())
                    .toUriString();

            return new FileResponse(
                    savedFile.getName(),
                    fileDownloadUri,
                    savedFile.getType(),
                    savedFile.getData().length);
        } catch (IOException e){
            throw new FileFailedToStore();
        }
    }
    @Override
    public FileEntity getFile(FileId fileId) {
       return fileRepository.findById(fileId).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public List<FileResponse> getAllFilesInABucket(BucketId bucketId) {
        List<FileEntity> files = fileRepository.findAllByBucketId(bucketId);
        return files.stream().map(fileEntity -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/buckets/download/")
                    .path(fileEntity.getId().getId())
                    .toUriString();

            return new FileResponse(
                    fileEntity.getName(),
                    fileDownloadUri,
                    fileEntity.getType(),
                    fileEntity.getData().length);
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(FileId fileId) {
        FileEntity fileEntity = getFile(fileId);
        eventPublisher.publish(new DecreaseBucketEvent(fileEntity.getBucketId().getId(), fileEntity.getSize().getSize()));
        fileRepository.delete(fileEntity);
    }
}