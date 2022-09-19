package mk.ukim.finki.storagemanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.storagemanagement.domain.valueobjects.UserId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buckets")
@Getter
public class BucketEntity extends AbstractEntity<BucketId> {

    private String name;

    @Embedded
    private ObjectSize size;

    @OneToMany(fetch = FetchType.EAGER)
    private List<FileEntity> files;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private UserId userId;

    public BucketEntity(@NonNull UserId userId, String name) {
        super(DomainObjectId.randomId(BucketId.class));
        this.userId = userId;
        this.name = name;
        this.size = new ObjectSize(0L);
        this.files = new ArrayList<>();
    }

    public BucketEntity() {
    }

    public void addFileToBucket(FileEntity fileEntity) {
        files.add(fileEntity);
        size = size.add(fileEntity.getSize());
    }
}
