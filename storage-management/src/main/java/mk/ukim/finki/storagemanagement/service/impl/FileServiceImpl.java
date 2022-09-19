package mk.ukim.finki.storagemanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.storagemanagement.domain.exceptions.FileNotFoundException;
import mk.ukim.finki.storagemanagement.domain.model.BucketEntity;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import mk.ukim.finki.storagemanagement.domain.model.FileEntity;
import mk.ukim.finki.storagemanagement.domain.model.FileId;
import mk.ukim.finki.storagemanagement.domain.repository.BucketRepository;
import mk.ukim.finki.storagemanagement.domain.repository.FileRepository;
import mk.ukim.finki.storagemanagement.service.BucketService;
import mk.ukim.finki.storagemanagement.service.FileService;
import mk.ukim.finki.storagemanagement.service.form.FileForm;
import mk.ukim.finki.storagemanagement.service.form.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final BucketService bucketService;

    @Override
    public FileResponse store(FileForm fileForm) throws IOException {
        MultipartFile multipartFile = fileForm.getMultipartFile();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        ObjectSize fileSize = new ObjectSize(multipartFile.getSize());
        FileEntity fileEntity = new FileEntity(fileName, fileForm.getMultipartFile().getContentType(), fileSize, multipartFile.getBytes());

        FileEntity savedFile = fileRepository.saveAndFlush(fileEntity);
        bucketService.addFileToBucket(savedFile, fileForm.getBucketId());

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
    }

    @Override
    public FileEntity getFile(FileId fileId) {
       return fileRepository.findById(fileId).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public List<FileEntity> getAllFiles() {
        return null;
    }
}