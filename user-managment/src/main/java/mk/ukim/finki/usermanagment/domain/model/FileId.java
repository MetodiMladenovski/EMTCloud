package mk.ukim.finki.usermanagment.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class FileId extends DomainObjectId {
    private FileId() {
        super(FileId.randomId(FileId.class).getId());
    }

    public FileId(@NonNull String uuid) {
        super(uuid);
    }
}
