package com.platform.recommendor.app.presentation;

import com.platform.recommendor.app.application.dto.user.LoginRequest;
import com.platform.recommendor.app.application.dto.user.LoginResponse;
import com.platform.recommendor.app.application.dto.user.UserRequest;
import com.platform.recommendor.app.application.dto.user.UserResponse;
import com.platform.recommendor.app.application.usecases.UserLoginUseCase;
import com.platform.recommendor.app.application.usecases.UserRegisterUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRegisterUseCase userRegister;
    private final UserLoginUseCase userLogin;

    public UserController(UserRegisterUseCase userRegister,  UserLoginUseCase userLogin) {
        this.userRegister = userRegister;
        this.userLogin = userLogin;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        return userRegister.registerUser(userRequest);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest userRequest) {
        return userLogin.login(userRequest);
    }

}
