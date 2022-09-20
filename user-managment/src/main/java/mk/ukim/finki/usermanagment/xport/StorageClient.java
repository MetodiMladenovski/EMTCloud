package mk.ukim.finki.usermanagment.xport;

import mk.ukim.finki.usermanagment.domain.model.FileEntity;
import mk.ukim.finki.usermanagment.domain.model.FileId;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketEntity;
import mk.ukim.finki.usermanagment.domain.valueobjects.BucketId;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class StorageClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public StorageClient(@Value("${app.storage-management.url}") String serverUrl) {
        this.restTemplate = new RestTemplate();
        this.serverUrl = serverUrl;
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<BucketEntity> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/buckets").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<BucketEntity>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
