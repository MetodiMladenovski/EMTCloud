package mk.ukim.finki.sharedkernel.domain.sizes;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class ObjectSize implements ValueObject {

    private final long size;

    protected ObjectSize() {
        this.size = 0L;
    }

    public ObjectSize(long size) {
        this.size = size;
    }

    public static ObjectSize valueOf(long size) {
        return new ObjectSize(size);
    }

    public ObjectSize add(ObjectSize objectSize) {
        return new ObjectSize(size + objectSize.size);
    }

    public ObjectSize subtract(ObjectSize objectSize) {
        return new ObjectSize(size - objectSize.size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectSize objectSize = (ObjectSize) o;
        return size == objectSize.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}
