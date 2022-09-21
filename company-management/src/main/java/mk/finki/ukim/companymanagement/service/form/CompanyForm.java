package mk.finki.ukim.companymanagement.service.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyForm {
    String name;
    String cityAddress;
    String numberAddress;
    String streetAddress;
}
