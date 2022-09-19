package mk.ukim.finki.usermanagment.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.information.Address;
import mk.ukim.finki.usermanagment.domain.valueobjects.CompanyId;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@Getter
public class UserEntity extends AbstractEntity<UserId> {

    private String fullName;
    private String email;
    private String encryptedPassword;

    @Embedded
    private Address address;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "company_id", nullable = false))
    private CompanyId companyId;

    private Role role;

    public UserEntity() {
        super(UserId.randomId(UserId.class));
    }

    public UserEntity(@NonNull CompanyId companyId, String fullName, String email, String encryptedPassword, Address address, Role role){
        super(UserId.randomId(UserId.class));
        this.companyId = companyId;
        this.fullName = fullName;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.address = address;
        this.role = role;
    }
}
