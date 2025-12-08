package com.platform.recommendor.app.presentation;

import com.platform.common.application.dto.CommonResponse;
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

import java.time.LocalDateTime;

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
    public CommonResponse<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        return CommonResponse.success(userRegister.execute(userRequest), "User Registration Successful");
    }

    @PostMapping("/login")
    public CommonResponse<LoginResponse> loginUser(@RequestBody LoginRequest userRequest) {
        return CommonResponse.success(userLogin.execute(userRequest), "User Login Successful");
    }

}
