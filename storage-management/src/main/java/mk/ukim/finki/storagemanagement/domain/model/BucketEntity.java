package mk.ukim.finki.storagemanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buckets")
@Getter
public class BucketEntity extends AbstractEntity<BucketId> {

    private String name;

    @Embedded
    private ObjectSize size;

    @OneToMany
    private List<FileEntity> files;

    public BucketEntity(String name) {
        this.name = name;
        this.size = new ObjectSize(0L);
        this.files = new ArrayList<>();
    }

    public BucketEntity() {
    }

    public void addFileToBucket(FileEntity fileEntity) {
        files.add(fileEntity);
        size = size.add(fileEntity.getSize());
    }
}
