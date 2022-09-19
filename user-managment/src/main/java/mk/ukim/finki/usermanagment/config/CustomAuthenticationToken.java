package mk.ukim.finki.usermanagment.config;

import lombok.Getter;
import mk.ukim.finki.usermanagment.service.form.LoggedUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private LoggedUser loggedUserDto;

    public CustomAuthenticationToken(
            LoggedUser loggedUserDto, Collection<? extends GrantedAuthority> authorities) {
        super(loggedUserDto.getUsername(), loggedUserDto.getPassword(), authorities);
        this.loggedUserDto = loggedUserDto;
    }

    @Override
    public LoggedUser getPrincipal() {
        return loggedUserDto;
    }
}
