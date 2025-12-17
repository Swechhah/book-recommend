package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.user.UserRequest;
import com.platform.recommendor.app.application.dto.user.UserResponse;
import com.platform.recommendor.app.domain.events.UserRegisteredEvent;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.domain.ports.out.DomainPublisherEvent;
import com.platform.recommendor.app.domain.ports.out.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterUseCase {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final DomainPublisherEvent publisher;
    public UserRegisterUseCase(UserRepository repository,  PasswordEncoder encoder
    , DomainPublisherEvent publisher) {
        this.repository = repository;
        this.encoder = encoder;
        this.publisher = publisher;
    }

    @Transactional
    public UserResponse execute(UserRequest userRequest) {

        UserModel user = new UserModel();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setRole(UserModel.UserRole.USER);

        UserModel savedUser = repository.saveUser(user);
        publisher.publish(new UserRegisteredEvent(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail()));
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setUsername(savedUser.getUsername());
        return userResponse;

    }


}
