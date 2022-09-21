package mk.finki.ukim.companymanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.companymanagement.domain.exceptions.CompanyNotFoundException;
import mk.finki.ukim.companymanagement.domain.model.CompanyEntity;
import mk.finki.ukim.companymanagement.domain.model.CompanyId;
import mk.finki.ukim.companymanagement.domain.repository.CompanyRepository;
import mk.finki.ukim.companymanagement.service.CompanyService;
import mk.finki.ukim.companymanagement.service.form.CompanyForm;
import mk.ukim.finki.sharedkernel.domain.information.Address;
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

    @Override
    public CompanyForm createCompany(CompanyForm companyForm) {
        Address address = new Address(companyForm.getCityAddress(), companyForm.getNumberAddress(), companyForm.getStreetAddress());
        CompanyEntity companyEntity = new CompanyEntity(companyForm.getName(), address);
        companyRepository.save(companyEntity);
        return companyForm;
    }

    @Override
    public void addEmployee(CompanyId companyId) {
        CompanyEntity companyEntity = companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        companyEntity.addEmployee();
        companyRepository.save(companyEntity);
    }
}
