package mk.finki.ukim.companymanagement.service;

import mk.finki.ukim.companymanagement.domain.model.CompanyEntity;
import mk.finki.ukim.companymanagement.domain.model.CompanyId;
import mk.finki.ukim.companymanagement.service.form.CompanyForm;

import java.util.List;

public interface CompanyService {
    List<CompanyEntity> findAll();
    CompanyForm createCompany(CompanyForm companyForm);
    void addEmployee(CompanyId companyId);
}
