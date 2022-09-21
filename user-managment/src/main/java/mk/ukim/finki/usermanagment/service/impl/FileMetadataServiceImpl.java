package mk.ukim.finki.usermanagment.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.events.storage.DecreaseBucketEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.IncreaseBucketEvent;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import mk.ukim.finki.usermanagment.domain.exceptions.FileFailedToStore;
import mk.ukim.finki.usermanagment.domain.exceptions.FileNotFoundException;
import mk.ukim.finki.usermanagment.domain.model.FileContentEntity;
import mk.ukim.finki.usermanagment.domain.model.FileMetadataEntity;
import mk.ukim.finki.usermanagment.domain.model.FileMetadataId;
import mk.ukim.finki.usermanagment.domain.repository.FileMetadataRepository;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import mk.ukim.finki.usermanagment.service.FileContentService;
import mk.ukim.finki.usermanagment.service.FileMetadataService;
import mk.ukim.finki.usermanagment.service.form.FileDownloadResponse;
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
public class FileMetadataServiceImpl implements FileMetadataService {

    private final FileMetadataRepository fileMetadataRepository;
    private final FileContentService fileContentService;
    private final DomainEventPublisher eventPublisher;


    @Override
    public FileMetadataEntity findById(FileMetadataId fileMetadataId) {
        return fileMetadataRepository.findById(fileMetadataId).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public FileResponse store(FileForm fileForm) {
        MultipartFile multipartFile = fileForm.getMultipartFile();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        ObjectSize fileSize = new ObjectSize(multipartFile.getSize());

        try {
            FileContentEntity fileContentEntity = new FileContentEntity(multipartFile.getBytes());
            FileContentEntity savedFileContent = fileContentService.save(fileContentEntity);
            FileMetadataEntity fileMetadataEntity = new FileMetadataEntity(fileName, fileForm.getMultipartFile().getContentType(), fileSize, savedFileContent.getId(), fileForm.getBucketId());
            FileMetadataEntity savedFile = fileMetadataRepository.saveAndFlush(fileMetadataEntity);
            eventPublisher.publish(new IncreaseBucketEvent(fileForm.getBucketId().getId(), fileSize.getSize()));

            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/buckets/download/")
                    .path(savedFile.getId().getId())
                    .toUriString();

            return new FileResponse(
                    savedFile.getId(),
                    savedFile.getName(),
                    fileDownloadUri,
                    savedFile.getType(),
                    savedFile.getContentId().getId());
        } catch (IOException e) {
            throw new FileFailedToStore();
        }
    }

    @Override
    public FileDownloadResponse downloadFile(FileMetadataId fileMetadataId) {
        FileMetadataEntity fileMetadataEntity = fileMetadataRepository.findById(fileMetadataId).orElseThrow(FileNotFoundException::new);
        FileContentEntity fileContentEntity = fileContentService.findById(fileMetadataEntity.getContentId());
        FileDownloadResponse fileDownloadResponse = new FileDownloadResponse(fileMetadataEntity.getName(),
                fileMetadataEntity.getType(),
                fileMetadataEntity.getSize(),
                fileContentEntity.getData(),
                fileMetadataEntity.getBucketId());
        return fileDownloadResponse;
    }

    @Override
    public List<FileResponse> getAllFilesInABucket(BucketId bucketId) {
        List<FileMetadataEntity> files = fileMetadataRepository.findAllByBucketId(bucketId);
        return files.stream().map(fileEntity -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/buckets/download/")
                    .path(fileEntity.getId().getId())
                    .toUriString();

            return new FileResponse(
                    fileEntity.getId(),
                    fileEntity.getName(),
                    fileDownloadUri,
                    fileEntity.getType(),
                    fileEntity.getContentId().getId());
        }).collect(Collectors.toList());
    }

    @Override
    public void delete(FileMetadataId fileMetadataId) {
        FileMetadataEntity fileMetadataEntity = findById(fileMetadataId);
        fileContentService.delete(fileMetadataEntity.getContentId());
        fileMetadataRepository.delete(fileMetadataEntity);
        eventPublisher.publish(new DecreaseBucketEvent(fileMetadataEntity.getBucketId().getId(), fileMetadataEntity.getSize().getSize()));
    }
}