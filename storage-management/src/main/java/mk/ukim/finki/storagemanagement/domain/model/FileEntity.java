package mk.ukim.finki.storagemanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "files")
@Getter
public class FileEntity extends AbstractEntity<FileId> {

    private String name;

    private String type;

    @Embedded
    private ObjectSize size;

    //mongoDB ID
    @Lob
    private byte[] data;

    public FileEntity() {
    }

    public FileEntity(String name, String type, ObjectSize size, byte[] data) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.data = data;
    }
}
