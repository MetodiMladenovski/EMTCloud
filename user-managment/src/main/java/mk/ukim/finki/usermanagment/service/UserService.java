package mk.ukim.finki.usermanagment.service;

import mk.ukim.finki.usermanagment.domain.model.UserEntity;
import mk.ukim.finki.usermanagment.domain.model.UserId;
import mk.ukim.finki.usermanagment.service.form.UserRegisterRequest;
import mk.ukim.finki.usermanagment.service.form.UserResponse;

public interface UserService {
    UserEntity findById(UserId userId);
    UserEntity findByEmail(String email);

    UserResponse registerUser(UserRegisterRequest userRegisterRequest);
}
