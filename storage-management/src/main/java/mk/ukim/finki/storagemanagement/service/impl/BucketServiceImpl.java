package mk.ukim.finki.storagemanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.storagemanagement.domain.exceptions.BucketNotEmptyException;
import mk.ukim.finki.storagemanagement.domain.exceptions.BucketNotFoundException;
import mk.ukim.finki.storagemanagement.domain.model.BucketEntity;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import mk.ukim.finki.storagemanagement.domain.model.FileEntity;
import mk.ukim.finki.storagemanagement.domain.repository.BucketRepository;
import mk.ukim.finki.storagemanagement.service.BucketService;
import mk.ukim.finki.storagemanagement.service.form.BucketForm;
import mk.ukim.finki.storagemanagement.service.form.FileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BucketServiceImpl implements BucketService {

    private final BucketRepository bucketRepository;

    @Override
    public BucketEntity findById(BucketId bucketId) {
        return bucketRepository.findById(bucketId).orElseThrow(BucketNotFoundException::new);
    }

    @Override
    public void addFileToBucket(FileEntity file, BucketId bucketId) {
        BucketEntity bucketEntity = findById(bucketId);
        bucketEntity.addFileToBucket(file);
        bucketRepository.save(bucketEntity);
    }

    @Override
    public List<BucketEntity> getAll() {
        return bucketRepository.findAll();
    }

    @Override
    public List<FileResponse> getBucketContent(BucketId bucketId) {
        BucketEntity bucketEntity = this.bucketRepository.findById(bucketId).orElseThrow(BucketNotFoundException::new);
        return bucketEntity.getFiles().stream().map(fileEntity -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(fileEntity.getId().toString())
                    .toUriString();

            return new FileResponse(
                    fileEntity.getName(),
                    fileDownloadUri,
                    fileEntity.getType(),
                    fileEntity.getData().length);
        }).collect(Collectors.toList());
    }

    @Override
    public BucketEntity createBucket(BucketForm bucketForm) {
        BucketEntity bucketEntity = new BucketEntity(bucketForm.getName());
        this.bucketRepository.save(bucketEntity);
        return bucketEntity;
    }

    @Override
    public void deleteBucket(BucketId bucketId) {
        BucketEntity bucketEntity = this.bucketRepository.findById(bucketId).orElseThrow(BucketNotFoundException::new);
        if(bucketEntity.getFiles().size() != 0){
            throw new BucketNotEmptyException();
        }
        this.bucketRepository.delete(bucketEntity);
    }
}
