package mk.ukim.finki.storagemanagement.service;

import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import mk.ukim.finki.storagemanagement.domain.model.FileEntity;
import mk.ukim.finki.storagemanagement.domain.model.FileId;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileEntity store(MultipartFile multipartFile, BucketId bucketId) throws IOException;
    FileEntity getFile(FileId fileId);
    List<FileEntity> getAllFiles();
}
