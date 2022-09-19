package mk.ukim.finki.storagemanagement.service.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileForm {
    private MultipartFile multipartFile;
    private BucketId bucketId;
}
