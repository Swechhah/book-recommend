package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.user.UserRequest;
import com.platform.recommendor.app.application.dto.user.UserResponse;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.BookRecommendorRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserRegisterUseCase {
    private final BookRecommendorRepository repository;
    private final BCryptPasswordEncoder encoder;
    public UserRegisterUseCase(BookRecommendorRepository repository,  BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional
    public UserResponse registerUser(UserRequest userRequest) {
        UserModel user = new UserModel();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encoder.encode(user.getPassword()));

        UserModel savedUser = repository.saveUser(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setEmail(savedUser.getEmail());
        return userResponse;

    }
}
