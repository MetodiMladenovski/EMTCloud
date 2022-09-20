package mk.ukim.finki.storagemanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.storagemanagement.domain.valueobjects.UserId;

import javax.persistence.*;

@Entity
@Table(name = "buckets")
@Getter
public class BucketEntity extends AbstractEntity<BucketId> {

    private String name;

    @Embedded
    private ObjectSize size;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private UserId userId;

    private Integer numberOfFilesInBucket;

    public BucketEntity(@NonNull UserId userId, String name) {
        super(DomainObjectId.randomId(BucketId.class));
        this.userId = userId;
        this.name = name;
        this.size = new ObjectSize(0L);
        this.numberOfFilesInBucket = 0;
    }

    public BucketEntity() {
    }

    public void increaseSizeAndNumber(long fileSize) {
        size = size.add(new ObjectSize(fileSize));
        ++numberOfFilesInBucket;
    }

    public void decreaseSizeAndNumber(long fileSize) {
        size = size.subtract(new ObjectSize(fileSize));
        --numberOfFilesInBucket;
    }
}
