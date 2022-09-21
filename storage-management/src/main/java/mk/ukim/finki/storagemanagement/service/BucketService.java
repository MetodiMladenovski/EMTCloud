package mk.ukim.finki.storagemanagement.service;

import mk.ukim.finki.storagemanagement.domain.model.BucketEntity;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import mk.ukim.finki.storagemanagement.service.form.BucketForm;

import java.util.List;

public interface BucketService {

    BucketEntity findById(BucketId bucketId);
    List<BucketEntity> getAll();
    BucketEntity createBucket(BucketForm bucketForm);

    void increaseSizeAndNumber(String bucketId, long fileSize);
    void decreaseSizeAndNumber(String bucketId, long fileSize);

    void deleteBucket(BucketId bucketId);
}
