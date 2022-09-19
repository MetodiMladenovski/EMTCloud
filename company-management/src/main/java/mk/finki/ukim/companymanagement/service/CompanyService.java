package mk.finki.ukim.companymanagement.service;

import mk.finki.ukim.companymanagement.domain.model.CompanyEntity;

import java.util.List;

public interface CompanyService {
    List<CompanyEntity> findAll();
}
