package mk.ukim.finki.usermanagment.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;

import javax.persistence.*;

@Entity
@Table(name = "files_metadata")
@Getter
public class FileMetadataEntity extends AbstractEntity<FileMetadataId> {

    private String name;

    private String type;

    @Embedded
    private ObjectSize size;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "content_id", nullable = false))
    private FileContentId contentId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "bucket_id", nullable = false))
    private BucketId bucketId;

    public FileMetadataEntity() {
    }

    public FileMetadataEntity(String name, String type, ObjectSize size, FileContentId contentId, BucketId bucketId) {
        super(DomainObjectId.randomId(FileMetadataId.class));
        this.name = name;
        this.type = type;
        this.size = size;
        this.contentId = contentId;
        this.bucketId = bucketId;
    }
}
