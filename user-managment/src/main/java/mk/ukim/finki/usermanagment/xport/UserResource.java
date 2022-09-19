package mk.ukim.finki.usermanagment.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usermanagment.domain.model.UserEntity;
import mk.ukim.finki.usermanagment.domain.model.UserId;
import mk.ukim.finki.usermanagment.service.UserService;
import mk.ukim.finki.usermanagment.service.form.UserRegisterRequest;
import mk.ukim.finki.usermanagment.service.form.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody UserRegisterRequest userRegisterRequest) {

        ResponseEntity<UserResponse> responseEntity =
                new ResponseEntity<>(userService.registerUser(userRegisterRequest), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/users")
    public ResponseEntity<String> findById(){
        return ResponseEntity.ok("All is fine in the world");
    }
}
