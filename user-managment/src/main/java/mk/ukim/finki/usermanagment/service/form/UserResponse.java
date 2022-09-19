package mk.ukim.finki.usermanagment.service.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.information.Address;
import mk.ukim.finki.usermanagment.domain.model.Role;
import mk.ukim.finki.usermanagment.domain.model.UserId;
import mk.ukim.finki.usermanagment.domain.valueobjects.CompanyId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UserId id;
    private String email;
    private String fullName;
    private Address address;
    private CompanyId companyId;
    private Role role;
}
