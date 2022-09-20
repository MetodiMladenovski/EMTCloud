package mk.ukim.finki.usermanagment.service.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileForm {
    private MultipartFile multipartFile;
    private BucketId bucketId;
}
