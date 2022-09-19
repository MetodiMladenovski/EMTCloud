package mk.ukim.finki.usermanagment.config.filter;

import mk.ukim.finki.usermanagment.util.JwtUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(
                        String.format(
                                "http://%s:%s", "localhost", 3000))
                .allowedOriginPatterns(
                        String.format(
                                "http://%s:%s/*", "localhost", 3000))
                .allowedHeaders(
                        JwtUtils.AUTH_HEADER,
                        HttpHeaders.ORIGIN,
                        HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT)
                .exposedHeaders(
                        JwtUtils.AUTH_HEADER,
                        HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                        HttpHeaders.ORIGIN,
                        HttpHeaders.ACCEPT,
                        HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
                        HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS,
                        HttpHeaders.CONTENT_DISPOSITION);
    }
}
