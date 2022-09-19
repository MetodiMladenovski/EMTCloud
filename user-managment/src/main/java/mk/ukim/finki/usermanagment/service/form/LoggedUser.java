package mk.ukim.finki.usermanagment.service.form;

import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.information.Address;
import mk.ukim.finki.usermanagment.domain.model.UserId;
import mk.ukim.finki.usermanagment.domain.valueobjects.CompanyId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

@Getter
public class LoggedUser extends User {
    private final UserId id;
    private final String fullName;
    private final String email;
    private final Address address;
    private final CompanyId companyId;

    public LoggedUser(
            UserId id,
            String fullName,
            Address address,
            CompanyId companyId,
            String email,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(
                email,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.companyId = companyId;
    }

    public boolean hasRole(String roleName) {
        return this.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(e -> e.equals(roleName));
    }

    public List<String> getRoleNames() {
        return super.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
