package mk.ukim.finki.sharedkernel.domain.information;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Address implements ValueObject {
    private String city;
    private String number;
    private String street;

    public Address(String city, String number, String street) {
        this.city = city;
        this.number = number;
        this.street = street;
    }

    public Address() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(number, address.number) &&
                Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, number, street);
    }
}
