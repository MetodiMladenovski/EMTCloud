package mk.ukim.finki.usermanagment.service.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mk.ukim.finki.usermanagment.domain.model.UserId;

@AllArgsConstructor
@Getter
public class JwtObject {
    UserId id;
    String email;

    public String getIdAsString() {
        return id.getId();
    }
}