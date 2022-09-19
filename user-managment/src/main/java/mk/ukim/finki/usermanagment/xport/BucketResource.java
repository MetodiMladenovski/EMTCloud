package mk.ukim.finki.usermanagment.xport;

import lombok.AllArgsConstructor;
import mk.ukim.finki.usermanagment.domain.model.UserEntity;
import mk.ukim.finki.usermanagment.domain.model.UserId;
import mk.ukim.finki.usermanagment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/buckets")
@AllArgsConstructor
public class BucketResource {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<String> findById(){
        return ResponseEntity.ok("All is fine in the world");
    }
}
