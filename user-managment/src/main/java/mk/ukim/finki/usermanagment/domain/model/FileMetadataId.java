package mk.ukim.finki.usermanagment.domain.model;

import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class FileMetadataId extends DomainObjectId {
    private FileMetadataId() {
        super(FileMetadataId.randomId(FileMetadataId.class).getId());
    }

    public FileMetadataId(@NonNull String uuid) {
        super(uuid);
    }
}
