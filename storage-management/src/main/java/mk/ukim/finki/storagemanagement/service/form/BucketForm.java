package mk.ukim.finki.storagemanagement.service.form;

import lombok.Data;
import mk.ukim.finki.storagemanagement.domain.valueobjects.UserEntity;
import mk.ukim.finki.storagemanagement.domain.valueobjects.UserId;

@Data
public class BucketForm {
    private String name;
    private UserId userId;
}
