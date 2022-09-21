package mk.ukim.finki.usermanagment.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usermanagment.domain.exceptions.FileContentEntityNotFound;
import mk.ukim.finki.usermanagment.domain.model.FileContentEntity;
import mk.ukim.finki.usermanagment.domain.model.FileContentId;
import mk.ukim.finki.usermanagment.domain.repository.FileContentRepository;
import mk.ukim.finki.usermanagment.service.FileContentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileContentServiceImpl implements FileContentService {

    private final FileContentRepository fileContentRepository;

    @Override
    public FileContentEntity findById(FileContentId fileContentId) {
        return fileContentRepository.findById(fileContentId).orElseThrow(FileContentEntityNotFound::new);
    }

    @Override
    public FileContentEntity save(FileContentEntity fileContentEntity) {
        return fileContentRepository.save(fileContentEntity);
    }

    @Override
    public void delete(FileContentId fileContentId) {
        fileContentRepository.deleteById(fileContentId);
    }
}
