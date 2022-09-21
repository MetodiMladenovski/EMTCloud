package mk.ukim.finki.usermanagment.service;

import mk.ukim.finki.usermanagment.domain.model.FileMetadataEntity;
import mk.ukim.finki.usermanagment.domain.model.FileMetadataId;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import mk.ukim.finki.usermanagment.service.form.FileDownloadResponse;
import mk.ukim.finki.usermanagment.service.form.FileForm;
import mk.ukim.finki.usermanagment.service.form.FileResponse;

import java.util.List;

public interface FileMetadataService {
    FileMetadataEntity findById(FileMetadataId fileMetadataId);
    FileResponse store(FileForm fileForm);
    FileDownloadResponse downloadFile(FileMetadataId fileMetadataId);
    List<FileResponse> getAllFilesInABucket(BucketId bucketId);
    void delete(FileMetadataId fileMetadataId);
}
