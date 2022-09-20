package mk.ukim.finki.usermanagment.domain.valueobjects;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class BucketId extends DomainObjectId {
    protected BucketId() {
        super(BucketId.randomId(BucketId.class).getId());
    }

    public BucketId(@NonNull String uuid) {
        super(uuid);
    }
}
