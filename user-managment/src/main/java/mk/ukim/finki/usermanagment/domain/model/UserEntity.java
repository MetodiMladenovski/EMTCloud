package mk.ukim.finki.usermanagment.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.sharedkernel.domain.information.Address;

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

    public UserEntity() {
        super(UserId.randomId(UserId.class));
    }

    public UserEntity(String fullName, String email, String encryptedPassword, Address address){
        super(UserId.randomId(UserId.class));
        this.fullName = fullName;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.address = address;
    }
}
