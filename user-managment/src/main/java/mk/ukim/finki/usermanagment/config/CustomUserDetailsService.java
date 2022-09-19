package mk.ukim.finki.usermanagment.config;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.usermanagment.domain.model.UserEntity;
import mk.ukim.finki.usermanagment.service.form.LoggedUser;
import mk.ukim.finki.usermanagment.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userService.findByEmail(username);

        List<SimpleGrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().toString()));

        return new LoggedUser(
                userEntity.getId(),
                userEntity.getFullName(),
                userEntity.getAddress(),
                userEntity.getCompanyId(),
                userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}