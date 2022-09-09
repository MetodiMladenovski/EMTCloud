package mk.ukim.finki.storagemanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "buckets")
@Getter
public class BucketEntity extends AbstractEntity<BucketId> {

    private String name;

    @Embedded
    private ObjectSize size;
}
