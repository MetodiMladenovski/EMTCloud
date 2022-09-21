package mk.ukim.finki.usermanagment.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class FileContentId extends DomainObjectId {
    private FileContentId() {
        super(FileContentId.randomId(FileContentId.class).getId());
    }

    public FileContentId(@NonNull String uuid) {
        super(uuid);
    }
}
