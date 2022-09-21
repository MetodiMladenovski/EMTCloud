package mk.ukim.finki.usermanagment.domain.repository;

import mk.ukim.finki.usermanagment.domain.model.FileMetadataEntity;
import mk.ukim.finki.usermanagment.domain.model.FileMetadataId;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileMetadataRepository extends JpaRepository<FileMetadataEntity, FileMetadataId> {
    List<FileMetadataEntity> findAllByBucketId(BucketId bucketId);
}
