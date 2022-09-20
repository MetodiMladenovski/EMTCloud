package mk.ukim.finki.storagemanagement.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.DecreaseBucketEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.IncreaseBucketEvent;
import mk.ukim.finki.storagemanagement.service.BucketService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StorageEventListener {

    private final BucketService bucketService;

    @KafkaListener(topics = TopicHolder.TOPIC_INCREASE_BUCKET_SIZE_AND_NUMBER_OF_FILES, groupId = "storageManagement")
    public void consumeIncreaseBucketEvent(String jsonMessage) {
        try {
            IncreaseBucketEvent event = DomainEvent.fromJson(jsonMessage, IncreaseBucketEvent.class);
            bucketService.increaseSizeAndNumber(event.getBucketId(), event.getFileSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_DECREASE_BUCKET_SIZE_AND_NUMBER_OF_FILES, groupId = "storageManagement")
    public void consumeDecreaseBucketEvent(String jsonMessage) {
        try {
            DecreaseBucketEvent event = DomainEvent.fromJson(jsonMessage, DecreaseBucketEvent.class);
            bucketService.decreaseSizeAndNumber(event.getBucketId(), event.getFileSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
