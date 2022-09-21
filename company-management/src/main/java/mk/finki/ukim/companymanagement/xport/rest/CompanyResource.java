package mk.finki.ukim.companymanagement.xport.rest;

import lombok.AllArgsConstructor;
import mk.finki.ukim.companymanagement.domain.model.CompanyEntity;
import mk.finki.ukim.companymanagement.service.CompanyService;
import mk.finki.ukim.companymanagement.service.form.CompanyForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<CompanyForm> createCompany(@RequestBody CompanyForm companyForm){
        CompanyForm savedCompany = companyService.createCompany(companyForm);
        return ResponseEntity.ok(savedCompany);
    }
}
