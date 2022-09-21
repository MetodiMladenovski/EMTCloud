package mk.ukim.finki.usermanagment.domain.repository;

import mk.ukim.finki.usermanagment.domain.model.FileContentEntity;
import mk.ukim.finki.usermanagment.domain.model.FileContentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileContentRepository extends JpaRepository<FileContentEntity, FileContentId> {
}
