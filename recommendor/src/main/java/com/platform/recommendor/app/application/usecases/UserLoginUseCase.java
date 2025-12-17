package com.platform.recommendor.app.application.usecases;

import com.platform.recommendor.app.application.dto.user.LoginRequest;
import com.platform.recommendor.app.application.dto.user.LoginResponse;
import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.domain.ports.out.UserRepository;
import com.platform.recommendor.app.infrastructure.auth.JwUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserLoginUseCase {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final JwUtil jwUtil;

    UserLoginUseCase(AuthenticationManager authenticationManager, UserRepository repository, JwUtil jwUtil) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwUtil = jwUtil;
    }

    public LoginResponse execute(LoginRequest userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword())
        );

        UserModel user = repository.getUserByUsername(userDto.getUsername());
        String authToken = jwUtil.generateToken(user.getUsername());

        return new LoginResponse(authToken);

    }

}
