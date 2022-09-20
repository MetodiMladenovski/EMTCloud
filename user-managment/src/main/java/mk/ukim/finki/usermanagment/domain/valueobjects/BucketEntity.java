package mk.ukim.finki.usermanagment.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.sizes.ObjectSize;
import mk.ukim.finki.usermanagment.domain.model.UserId;

@Getter
public class BucketEntity implements ValueObject {
    private final BucketId id;
    private final String name;
    private final ObjectSize size;
    private final UserId userId;
    private final Integer numberOfFilesInBucket;

    public BucketEntity() {
        this.id = BucketId.randomId(BucketId.class);
        this.name = "";
        this.size = new ObjectSize(0L);
        userId = UserId.randomId(UserId.class);
        this.numberOfFilesInBucket = 0;
    }

    @JsonCreator
    public BucketEntity(@JsonProperty("id") BucketId id,
                        @JsonProperty("name") String name,
                        @JsonProperty("size") ObjectSize size,
                        @JsonProperty("userId") UserId userId,
                        @JsonProperty("numberOfFilesInBucket") Integer numberOfFilesInBucket) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.userId = userId;
        this.numberOfFilesInBucket = numberOfFilesInBucket;
    }
}
