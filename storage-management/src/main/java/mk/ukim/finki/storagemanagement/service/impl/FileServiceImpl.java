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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final BucketService bucketService;

    @Override
    public FileEntity store(MultipartFile multipartFile, BucketId bucketId) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        ObjectSize fileSize = new ObjectSize(multipartFile.getSize());
        FileEntity fileEntity = new FileEntity(fileName, multipartFile.getContentType(), fileSize, multipartFile.getBytes());

        bucketService.addFileToBucket(fileEntity, bucketId);
        return fileRepository.save(fileEntity);
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