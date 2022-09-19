package mk.ukim.finki.storagemanagement.service;

import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import mk.ukim.finki.storagemanagement.domain.model.FileEntity;
import mk.ukim.finki.storagemanagement.domain.model.FileId;
import mk.ukim.finki.storagemanagement.service.form.FileForm;
import mk.ukim.finki.storagemanagement.service.form.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileResponse store(FileForm fileForm) throws IOException;
    FileEntity getFile(FileId fileId);
    List<FileEntity> getAllFiles();
}
