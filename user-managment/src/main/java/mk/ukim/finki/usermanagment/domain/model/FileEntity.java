package mk.ukim.finki.usermanagment.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Getter
public class FileEntity extends AbstractEntity<FileId> {

    private String name;

    private String type;

    @Embedded
    private ObjectSize size;

    //mongoDB ID
    @Lob
    private byte[] data;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "bucket_id", nullable = false))
    private BucketId bucketId;

    public FileEntity() {
    }

    public FileEntity(String name, String type, ObjectSize size, byte[] data, BucketId bucketId) {
        super(DomainObjectId.randomId(FileId.class));
        this.name = name;
        this.type = type;
        this.size = size;
        this.data = data;
        this.bucketId = bucketId;
    }
}
