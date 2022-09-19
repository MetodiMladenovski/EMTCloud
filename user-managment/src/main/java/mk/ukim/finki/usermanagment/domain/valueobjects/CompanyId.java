package mk.ukim.finki.usermanagment.domain.valueobjects;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CompanyId extends DomainObjectId {
    protected CompanyId() {
        super(CompanyId.randomId(CompanyId.class).getId());
    }

    public CompanyId(@NonNull String uuid) {
        super(uuid);
    }
}
