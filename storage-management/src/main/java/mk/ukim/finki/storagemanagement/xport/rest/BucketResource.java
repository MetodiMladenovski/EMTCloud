package mk.ukim.finki.storagemanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.storagemanagement.domain.model.BucketEntity;
import mk.ukim.finki.storagemanagement.service.BucketService;
import mk.ukim.finki.storagemanagement.service.form.BucketForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/buckets")
@AllArgsConstructor
public class BucketResource {

    private final BucketService bucketService;

    @GetMapping
    public List<BucketEntity> findAll() {
        return bucketService.getAll();
    }

}
