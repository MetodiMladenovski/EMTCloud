package mk.finki.ukim.companymanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.companymanagement.domain.model.CompanyEntity;
import mk.finki.ukim.companymanagement.domain.repository.CompanyRepository;
import mk.finki.ukim.companymanagement.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Override
    public List<CompanyEntity> findAll() {
        return companyRepository.findAll();
    }
}
