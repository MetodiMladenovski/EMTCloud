package mk.ukim.finki.sharedkernel.domain.events.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

@Getter
public class CreateBucketEvent extends DomainEvent {
    private String name;
    private String userId;

    public CreateBucketEvent(String topic) {
        super(TopicHolder.TOPIC_CREATE_BUCKET);
    }

    @JsonCreator
    public CreateBucketEvent(@JsonProperty("name") String name,
                             @JsonProperty("userId") String userId) {
        super(TopicHolder.TOPIC_CREATE_BUCKET);
        this.name = name;
        this.userId = userId;
    }
}
