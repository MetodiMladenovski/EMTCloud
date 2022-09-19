package mk.ukim.finki.usermanagment.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.log4j.Log4j2;
import mk.ukim.finki.usermanagment.domain.exceptions.InvalidAlgorithmException;
import mk.ukim.finki.usermanagment.domain.exceptions.InvalidSessionTokenException;
import mk.ukim.finki.usermanagment.service.form.JwtObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Log4j2
public class JwtUtils {

    public static final String AUTH_HEADER = HttpHeaders.AUTHORIZATION;
    public static long ACCESS_TOKEN_EXPIRATION_MS = 1800000;
    private static final String JWT_SECRET = "emtCloudApplication";
    public static final String TOKEN_PREFIX = "Bearer ";
    private static final String CLAIM_NAME = "roles";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(JWT_SECRET);




    private static String generate(
            JwtObject jwtObject, long msUntilExpiration, String claimName, List<String> claims) {
        return JWT.create()
                .withSubject(jwtObject.getEmail())
                .withPayload(Map.of("id", jwtObject.getIdAsString()))
                .withExpiresAt(new Date(System.currentTimeMillis() + msUntilExpiration))
                .withClaim(claimName, claims)
                .sign(ALGORITHM);
    }

    public static String generateAccessToken(JwtObject jwtObject, List<String> claims) {
        return generate(jwtObject, ACCESS_TOKEN_EXPIRATION_MS, CLAIM_NAME, claims);
    }

    public static UsernamePasswordAuthenticationToken getFrom(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);

            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            Collection<SimpleGrantedAuthority> authorities =
                    Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new InvalidSessionTokenException();
        } catch (IllegalArgumentException e) {
            log.error("JWT algorithm is null: {}", e.getMessage());
            throw new InvalidAlgorithmException();
        }
    }
}
