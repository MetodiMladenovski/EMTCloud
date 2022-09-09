package mk.ukim.finki.usermanagment.domain.model;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
@Getter
public class UserEntity extends AbstractEntity<UserId> {

    String fullName;
    String email;
    String encryptedPassword;

    public UserEntity() {
        super(UserId.randomId(UserId.class));
    }

    public UserEntity(String fullName, String email, String encryptedPassword){
        super(UserId.randomId(UserId.class));
        this.fullName = fullName;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
    }
}
