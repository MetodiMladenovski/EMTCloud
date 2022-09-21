package mk.ukim.finki.usermanagment.service;


import mk.ukim.finki.usermanagment.domain.model.FileContentEntity;
import mk.ukim.finki.usermanagment.domain.model.FileContentId;

public interface FileContentService {
    FileContentEntity findById(FileContentId fileContentId);
    FileContentEntity save(FileContentEntity fileContentEntity);
    void delete(FileContentId fileContentId);
}
