package mk.ukim.finki.storagemanagement.service;

import mk.ukim.finki.storagemanagement.domain.model.BucketEntity;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import mk.ukim.finki.storagemanagement.domain.model.FileEntity;
import mk.ukim.finki.storagemanagement.service.form.BucketForm;
import mk.ukim.finki.storagemanagement.service.form.FileResponse;

import java.util.List;

public interface BucketService {

    BucketEntity findById(BucketId bucketId);
    void addFileToBucket(FileEntity file, BucketId bucketId);
    List<BucketEntity> getAll();
    List<FileResponse> getBucketContent(BucketId bucketId);
    BucketEntity createBucket(BucketForm bucketForm);
    void deleteBucket(BucketId bucketId);
}
