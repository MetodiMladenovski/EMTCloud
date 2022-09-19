package mk.finki.ukim.companymanagement.domain.repository;

import mk.finki.ukim.companymanagement.domain.model.CompanyEntity;
import mk.finki.ukim.companymanagement.domain.model.CompanyId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, CompanyId> {
}
