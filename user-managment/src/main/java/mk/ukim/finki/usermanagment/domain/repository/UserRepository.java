package mk.ukim.finki.usermanagment.domain.repository;

import mk.ukim.finki.usermanagment.domain.model.UserEntity;
import mk.ukim.finki.usermanagment.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, UserId> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
