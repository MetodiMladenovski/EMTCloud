package mk.ukim.finki.storagemanagement.domain.valueobjects;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {
    protected UserId() {
        super(UserId.randomId(UserId.class).getId());
    }

    public UserId(@NonNull String uuid) {
        super(uuid);
    }

    public static UserId of(String uuid) {
        return new UserId(uuid);
    }
}