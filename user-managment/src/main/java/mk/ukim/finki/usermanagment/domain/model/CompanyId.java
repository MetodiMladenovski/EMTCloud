package mk.ukim.finki.usermanagment.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class CompanyId extends DomainObjectId {
    private CompanyId() {
        super(CompanyId.randomId(CompanyId.class).getId());
    }

    public CompanyId(@NonNull String uuid) {
        super(uuid);
    }
}
