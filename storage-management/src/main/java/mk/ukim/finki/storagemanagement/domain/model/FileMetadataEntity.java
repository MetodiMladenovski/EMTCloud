package mk.ukim.finki.storagemanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "files_metadata")
@Getter
public class FileMetadataEntity extends AbstractEntity<FileMetadataId> {

    private String name;

    private String type;

    @Embedded
    private ObjectSize size;

    //mongoDB ID
    private String contentId;

}
