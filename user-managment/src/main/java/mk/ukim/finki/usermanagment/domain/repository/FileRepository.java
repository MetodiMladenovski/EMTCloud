package mk.ukim.finki.usermanagment.domain.repository;

import mk.ukim.finki.usermanagment.domain.model.FileEntity;
import mk.ukim.finki.usermanagment.domain.model.FileId;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, FileId> {
    List<FileEntity> findAllByBucketId(BucketId bucketId);
}
