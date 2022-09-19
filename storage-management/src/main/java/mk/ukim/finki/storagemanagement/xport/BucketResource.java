package mk.ukim.finki.storagemanagement.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.storagemanagement.domain.model.BucketEntity;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import mk.ukim.finki.storagemanagement.domain.model.FileEntity;
import mk.ukim.finki.storagemanagement.domain.model.FileId;
import mk.ukim.finki.storagemanagement.service.BucketService;
import mk.ukim.finki.storagemanagement.service.FileService;
import mk.ukim.finki.storagemanagement.service.form.BucketForm;
import mk.ukim.finki.storagemanagement.service.form.FileForm;
import mk.ukim.finki.storagemanagement.service.form.FileResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/buckets")
@AllArgsConstructor
public class BucketResource {

    private final BucketService bucketService;
    private final FileService fileService;

    @GetMapping
    public List<BucketEntity> findAll() {
        return bucketService.getAll();
    }

    @PostMapping
    public ResponseEntity<BucketEntity> createBucket(@Valid @RequestBody BucketForm bucketForm){
        return ResponseEntity.ok(bucketService.createBucket(bucketForm));
    }

    @PostMapping(path = "/upload/{bucketId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileResponse> uploadFileToBucket(
            @PathVariable BucketId bucketId, @RequestPart("file") MultipartFile file) throws IOException {

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
}
