package mk.finki.ukim.companymanagement.xport;

import lombok.AllArgsConstructor;
import mk.finki.ukim.companymanagement.domain.model.CompanyEntity;
import mk.finki.ukim.companymanagement.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
public class CompanyResource {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyEntity> findAll(){
        return companyService.findAll();
    }
}
