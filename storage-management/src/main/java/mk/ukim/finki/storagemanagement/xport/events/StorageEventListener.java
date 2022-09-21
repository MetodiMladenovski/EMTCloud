package mk.ukim.finki.storagemanagement.xport.events;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.CreateBucketEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.DecreaseBucketEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.DeleteBucketEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.IncreaseBucketEvent;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import mk.ukim.finki.storagemanagement.domain.valueobjects.UserId;
import mk.ukim.finki.storagemanagement.service.BucketService;
import mk.ukim.finki.storagemanagement.service.form.BucketForm;
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

    @KafkaListener(topics = TopicHolder.TOPIC_DELETE_BUCKET, groupId = "storageManagement")
    public void consumeDeleteBucketEvent(String jsonMessage) {
        try {
            DeleteBucketEvent event = DomainEvent.fromJson(jsonMessage, DeleteBucketEvent.class);
            bucketService.deleteBucket(BucketId.of(event.getBucketId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_CREATE_BUCKET, groupId = "storageManagement")
    public void consumeCreateBucketEvent(String jsonMessage) {
        try {
            CreateBucketEvent event = DomainEvent.fromJson(jsonMessage, CreateBucketEvent.class);
            BucketForm bucketForm = new BucketForm(event.getName(), UserId.of(event.getUserId()));
            bucketService.createBucket(bucketForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
