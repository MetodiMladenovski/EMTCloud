package mk.ukim.finki.sharedkernel.domain.events.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

@Getter
public class DeleteBucketEvent extends DomainEvent {
    private String bucketId;

    public DeleteBucketEvent(String topic) {
        super(TopicHolder.TOPIC_DELETE_BUCKET);
    }

    @JsonCreator
    public DeleteBucketEvent(@JsonProperty("topic") String topic, @JsonProperty("bucketId") String bucketId) {
        super(TopicHolder.TOPIC_DELETE_BUCKET);
        this.bucketId = bucketId;
    }
}
