package mk.ukim.finki.usermanagment.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.sharedkernel.domain.events.company.AddEmployeeEvent;
import mk.ukim.finki.sharedkernel.domain.information.Address;
import mk.ukim.finki.sharedkernel.infra.DomainEventPublisher;
import mk.ukim.finki.usermanagment.domain.exceptions.EmailAlreadyInUseException;
import mk.ukim.finki.usermanagment.domain.exceptions.UserNotFoundException;
import mk.ukim.finki.usermanagment.domain.model.Role;
import mk.ukim.finki.usermanagment.domain.model.UserEntity;
import mk.ukim.finki.usermanagment.domain.model.UserId;
import mk.ukim.finki.usermanagment.domain.repository.UserRepository;
import mk.ukim.finki.usermanagment.domain.valueobjects.CompanyId;
import mk.ukim.finki.usermanagment.service.UserService;
import mk.ukim.finki.usermanagment.service.form.UserRegisterRequest;
import mk.ukim.finki.usermanagment.service.form.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final DomainEventPublisher eventPublisher;

    @Override
    public UserEntity findById(UserId userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserResponse registerUser(UserRegisterRequest userRegisterRequest) {

        if (userRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new EmailAlreadyInUseException();
        }
        Address address = new Address(userRegisterRequest.getCityAddress(),
                userRegisterRequest.getNumberAddress(),
                userRegisterRequest.getStreetAddress());

        UserEntity userEntity = new UserEntity(CompanyId.of(userRegisterRequest.getCompanyId()),
                userRegisterRequest.getFullName(), userRegisterRequest.getEmail(),
                passwordEncoder.encode(userRegisterRequest.getPassword()),
                address, Role.ROLE_USER);

        userRepository.save(userEntity);
        eventPublisher.publish(new AddEmployeeEvent("", userEntity.getCompanyId().getId()));


        return new UserResponse(userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFullName(),
                userEntity.getAddress(),
                userEntity.getCompanyId(),
                Role.ROLE_USER);
    }
}
