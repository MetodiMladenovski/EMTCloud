package mk.ukim.finki.storagemanagement.service.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.storagemanagement.domain.valueobjects.UserEntity;
import mk.ukim.finki.storagemanagement.domain.valueobjects.UserId;

@Data
@AllArgsConstructor
public class BucketForm {
    private String name;
    private UserId userId;
}
