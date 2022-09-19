package mk.ukim.finki.storagemanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.information.Address;

@Getter
public class UserEntity implements ValueObject {
    private final UserId id;
    private final String fullName;
    private final String email;
    private final Address address;

    private UserEntity() {
        this.id = UserId.randomId(UserId.class);
        this.fullName = "";
        this.email = "";
        this.address = Address.valueOf("", "", "");
    }

    @JsonCreator
    public UserEntity(@JsonProperty("id") UserId id,
                      @JsonProperty("fullName") String fullName,
                      @JsonProperty("email") String email,
                      @JsonProperty("address") Address address) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
    }
}