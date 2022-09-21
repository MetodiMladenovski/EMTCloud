package mk.ukim.finki.usermanagment.service.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.usermanagment.domain.valueobjects.CompanyId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String repeatedPassword;
    private String cityAddress;
    private String numberAddress;
    private String streetAddress;
    private String companyId;
}
