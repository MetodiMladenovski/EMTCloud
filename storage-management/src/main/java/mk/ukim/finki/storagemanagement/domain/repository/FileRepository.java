package mk.ukim.finki.storagemanagement.domain.repository;

import mk.ukim.finki.storagemanagement.domain.model.FileEntity;
import mk.ukim.finki.storagemanagement.domain.model.FileId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, FileId> {
}
