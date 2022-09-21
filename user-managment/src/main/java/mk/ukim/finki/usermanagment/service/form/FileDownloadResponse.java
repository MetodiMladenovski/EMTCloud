package mk.ukim.finki.usermanagment.service.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;

@Data
@AllArgsConstructor
public class FileDownloadResponse {
    private String name;
    private String type;
    private ObjectSize size;
    private byte[] data;
    private BucketId bucketId;
}
