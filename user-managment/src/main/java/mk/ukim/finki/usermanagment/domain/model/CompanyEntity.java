package mk.ukim.finki.usermanagment.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.information.Address;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company")
@Getter
public class CompanyEntity extends AbstractEntity<CompanyId> {

    private String name;

    @Embedded
    private Address address;

    public CompanyEntity(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public CompanyEntity() {
    }
}
