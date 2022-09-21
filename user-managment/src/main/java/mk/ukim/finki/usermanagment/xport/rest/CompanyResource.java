package mk.ukim.finki.usermanagment.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usermanagment.domain.valueobjects.CompanyEntity;
import mk.ukim.finki.usermanagment.xport.CompanyClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
public class CompanyResource {
    private final CompanyClient companyClient;

    @GetMapping
    public List<CompanyEntity> findAll(){
        return companyClient.findAll();
    }
}
