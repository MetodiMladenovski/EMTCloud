package mk.ukim.finki.usermanagment.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.sharedkernel.domain.information.Address;

@Getter
public class CompanyEntity implements ValueObject {
    private final CompanyId companyId;
    private final String name;
    private final Address address;
    private final Integer numberOfEmployees;

    public CompanyEntity() {
        this.companyId = CompanyId.randomId(CompanyId.class);
        this.name = "";
        this.address = Address.valueOf("", "", "");
        this.numberOfEmployees = 0;
    }

    @JsonCreator
    public CompanyEntity(@JsonProperty("id")CompanyId companyId,
                         @JsonProperty("name") String name,
                         @JsonProperty("address") Address address,
                         @JsonProperty("numberOfEmployees") Integer numberOfEmployees) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
        this.numberOfEmployees = numberOfEmployees;
    }
}
