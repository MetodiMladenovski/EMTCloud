package mk.ukim.finki.usermanagment.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.information.Address;

@Getter
public class CompanyEntity implements ValueObject {
    private final String name;
    private final Address address;

    public CompanyEntity() {
        this.name = "";
        this.address = Address.valueOf("", "", "");
    }

    @JsonCreator
    public CompanyEntity(@JsonProperty("name") String name,
                         @JsonProperty("address") Address address) {
        this.name = name;
        this.address = address;
    }
}
