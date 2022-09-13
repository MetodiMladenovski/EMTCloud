package mk.ukim.finki.storagemanagement.domain.repository;

import mk.ukim.finki.storagemanagement.domain.model.BucketEntity;
import mk.ukim.finki.storagemanagement.domain.model.BucketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketEntity, BucketId> {
}
