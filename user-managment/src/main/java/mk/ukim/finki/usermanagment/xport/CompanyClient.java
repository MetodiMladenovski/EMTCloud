package mk.ukim.finki.usermanagment.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketEntity;
import mk.ukim.finki.usermanagment.domain.valueobjects.CompanyEntity;
import mk.ukim.finki.usermanagment.domain.valueobjects.CompanyId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class CompanyClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public CompanyClient(@Value("${app.company-management.url}") String serverUrl) {
        this.restTemplate = new RestTemplate();
        this.serverUrl = serverUrl;
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<CompanyEntity> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/company").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<CompanyEntity>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
