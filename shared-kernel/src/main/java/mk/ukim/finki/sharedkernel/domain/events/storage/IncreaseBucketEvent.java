package mk.ukim.finki.sharedkernel.domain.events.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

@Getter
@Setter
public class IncreaseBucketEvent extends DomainEvent {

    private long fileSize;
    private String bucketId;

    public IncreaseBucketEvent(String topic) {
        super(TopicHolder.TOPIC_INCREASE_BUCKET_SIZE_AND_NUMBER_OF_FILES);
    }

    @JsonCreator
    public IncreaseBucketEvent(@JsonProperty("bucketId") String bucketId,
                               @JsonProperty("fileSize") long fileSize) {
        super(TopicHolder.TOPIC_INCREASE_BUCKET_SIZE_AND_NUMBER_OF_FILES);
        this.fileSize = fileSize;
        this.bucketId = bucketId;
    }
}
