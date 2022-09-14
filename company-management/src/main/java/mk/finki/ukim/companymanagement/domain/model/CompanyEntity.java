package mk.finki.ukim.companymanagement.domain.model;

import lombok.Getter;
import lombok.experimental.ExtensionMethod;
import mk.finki.ukim.companymanagement.domain.valueobjects.UserEntity;
import mk.finki.ukim.companymanagement.domain.valueobjects.UserId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.information.Address;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
public class CompanyEntity extends AbstractEntity<CompanyId> {

    private String name;

    @Embedded
    private Address address;

    @Transient
    private List<UserId> users;

    public CompanyEntity(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public CompanyEntity() {
    }

    public void addUserToCompany(UserId userId) {
        users.add(userId);
    }

    public void removeUserFromCompany(UserId userId) {
        users.remove(userId);
    }
}