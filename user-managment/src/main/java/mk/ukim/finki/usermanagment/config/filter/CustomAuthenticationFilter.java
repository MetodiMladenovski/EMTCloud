package mk.ukim.finki.usermanagment.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mk.ukim.finki.usermanagment.domain.exceptions.IOErrorException;
import mk.ukim.finki.usermanagment.domain.exceptions.WrongPasswordException;
import mk.ukim.finki.usermanagment.service.form.JwtObject;
import mk.ukim.finki.usermanagment.service.form.LoggedUser;
import mk.ukim.finki.usermanagment.service.form.UserLoginRequest;
import mk.ukim.finki.usermanagment.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginRequest credentials =
                    objectMapper.readValue(request.getInputStream(), UserLoginRequest.class);
            UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getEmail());
            log.info("The user: {} is trying to authenticate.", userDetails.getUsername());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), credentials.getPassword(), userDetails.getAuthorities()));

        } catch (BadCredentialsException ex) {
            throw new WrongPasswordException();
        } catch (IOException e) {
            log.error("Unable to authenticate, because of Input or Output error", e);
            throw new IOErrorException();
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authentication) {

        LoggedUser user = (LoggedUser) authentication.getPrincipal();
        List<String> authorities = user.getRoleNames();

        JwtObject jwtObject = new JwtObject(user.getId(), user.getUsername());

        String token = JwtUtils.generateAccessToken(jwtObject, authorities);

        response.addHeader(JwtUtils.AUTH_HEADER, String.format("%s%s", JwtUtils.TOKEN_PREFIX, token));

        log.info("Jwt token successfully created for user: {}", user.getUsername());

        log.info("{} is successfully logged in.", user.getUsername());
    }
}