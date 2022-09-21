package mk.finki.ukim.companymanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.sharedkernel.domain.information.Address;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Getter
public class CompanyEntity extends AbstractEntity<CompanyId> {

    private String name;

    @Embedded
    private Address address;

    private Integer numberOfEmployees;

    public CompanyEntity(String name, Address address) {
        super(DomainObjectId.randomId(CompanyId.class));
        this.name = name;
        this.address = address;
        this.numberOfEmployees = 0;
    }

    public CompanyEntity() {
    }

    public void addEmployee(){
        ++numberOfEmployees;
    };
}