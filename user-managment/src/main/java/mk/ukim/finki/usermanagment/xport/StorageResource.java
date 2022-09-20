package mk.ukim.finki.usermanagment.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import mk.ukim.finki.usermanagment.domain.model.FileEntity;
import mk.ukim.finki.usermanagment.domain.model.FileId;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketEntity;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import mk.ukim.finki.usermanagment.service.FileService;
import mk.ukim.finki.usermanagment.service.UserService;
import mk.ukim.finki.usermanagment.service.form.FileForm;
import mk.ukim.finki.usermanagment.service.form.FileResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/buckets")
@AllArgsConstructor
public class StorageResource {

    private final FileService fileService;
    private final StorageClient storageClient;

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

    @GetMapping(path = "/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable FileId fileId) {
        FileEntity fileEntity = fileService.getFile(fileId);
        ResponseEntity<byte[]> responseEntity =
                ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(fileEntity.getType()))
                        .header(
                                HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + fileEntity.getName() + "\"")
                        .body(fileEntity.getData());
        return responseEntity;
    }

    @GetMapping("/{bucketId}")
    public List<FileResponse> getAllFilesInABucket(@PathVariable BucketId bucketId){
        return fileService.getAllFilesInABucket(bucketId);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Boolean> delete(@PathVariable FileId fileId){
        fileService.delete(fileId);
        return ResponseEntity.ok(true);
    }
}
