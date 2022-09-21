package mk.ukim.finki.usermanagment.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.events.storage.CreateBucketEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.DeleteBucketEvent;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import mk.ukim.finki.usermanagment.domain.model.FileMetadataEntity;
import mk.ukim.finki.usermanagment.domain.model.FileMetadataId;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketEntity;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import mk.ukim.finki.usermanagment.service.FileMetadataService;
import mk.ukim.finki.usermanagment.service.form.FileDownloadResponse;
import mk.ukim.finki.usermanagment.service.form.FileForm;
import mk.ukim.finki.usermanagment.service.form.FileResponse;
import mk.ukim.finki.usermanagment.xport.StorageClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/buckets")
@AllArgsConstructor
public class StorageResource {

    private final FileMetadataService fileService;
    private final StorageClient storageClient;
    private final DomainEventPublisher eventPublisher;

    @GetMapping
    public List<BucketEntity> findAllBuckets() {
        return storageClient.findAll();
    }

    @PostMapping(path = "/upload/{bucketId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileResponse> uploadFile(
            @PathVariable BucketId bucketId, @RequestPart("file") MultipartFile file) {
        FileForm fileForm = new FileForm(file, bucketId);
        FileResponse fileResponse = fileService.store(fileForm);
        return new ResponseEntity<>(fileResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/download/{fileMetadataId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable FileMetadataId fileMetadataId) {
        FileDownloadResponse fileDownloadResponse = fileService.downloadFile(fileMetadataId);
        ResponseEntity<byte[]> responseEntity =
                ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(fileDownloadResponse.getType()))
                        .header(
                                HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + fileDownloadResponse.getName() + "\"")
                        .body(fileDownloadResponse.getData());
        return responseEntity;
    }

    @GetMapping("/{bucketId}")
    public List<FileResponse> getAllFilesInABucket(@PathVariable BucketId bucketId) {
        return fileService.getAllFilesInABucket(bucketId);
    }

    @DeleteMapping("/file/{fileMetadataId}")
    public ResponseEntity<Boolean> delete(@PathVariable FileMetadataId fileMetadataId) {
        fileService.delete(fileMetadataId);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{bucketId}")
    public void deleteBucket(@PathVariable BucketId bucketId) {
        eventPublisher.publish(new DeleteBucketEvent("", bucketId.getId()));
    }

    @PostMapping("/{userId}")
    public void createBucket(@PathVariable String userId, @Valid @RequestParam String name) {
        eventPublisher.publish(new CreateBucketEvent(name, userId));
    }
}