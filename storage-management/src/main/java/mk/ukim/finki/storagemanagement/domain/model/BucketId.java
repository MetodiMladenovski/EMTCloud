package mk.ukim.finki.storagemanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class BucketId extends DomainObjectId {
    private BucketId() {
        super(BucketId.randomId(BucketId.class).getId());
    }

    public BucketId(@NonNull String uuid) {
        super(uuid);
    }
}
