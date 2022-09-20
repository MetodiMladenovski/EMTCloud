package mk.ukim.finki.usermanagment.service;

import mk.ukim.finki.usermanagment.domain.model.FileEntity;
import mk.ukim.finki.usermanagment.domain.model.FileId;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import mk.ukim.finki.usermanagment.service.form.FileForm;
import mk.ukim.finki.usermanagment.service.form.FileResponse;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileResponse store(FileForm fileForm);
    FileEntity getFile(FileId fileId);
    List<FileResponse> getAllFilesInABucket(BucketId bucketId);
    void delete(FileId fileId);
}
