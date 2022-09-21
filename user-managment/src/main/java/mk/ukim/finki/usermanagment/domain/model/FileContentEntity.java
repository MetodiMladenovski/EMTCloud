package mk.ukim.finki.usermanagment.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "files_content")
@Getter
public class FileContentEntity extends AbstractEntity<FileContentId> {

    @Lob
    private byte[] data;

    public FileContentEntity() {
    }

    public FileContentEntity(byte[] data) {
        super(DomainObjectId.randomId(FileContentId.class));
        this.data = data;
    }
}
