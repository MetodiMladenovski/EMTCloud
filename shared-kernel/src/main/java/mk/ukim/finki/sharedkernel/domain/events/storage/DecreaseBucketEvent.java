package mk.ukim.finki.sharedkernel.domain.events.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

@Getter
public class DecreaseBucketEvent extends DomainEvent {

    private long fileSize;
    private String bucketId;

    public DecreaseBucketEvent(String topic) {
        super(TopicHolder.TOPIC_DECREASE_BUCKET_SIZE_AND_NUMBER_OF_FILES);
    }

    @JsonCreator
    public DecreaseBucketEvent(@JsonProperty("bucketId") String bucketId,
                               @JsonProperty("fileSize") long fileSize) {
        super(TopicHolder.TOPIC_DECREASE_BUCKET_SIZE_AND_NUMBER_OF_FILES);
        this.fileSize = fileSize;
        this.bucketId = bucketId;
    }
}
