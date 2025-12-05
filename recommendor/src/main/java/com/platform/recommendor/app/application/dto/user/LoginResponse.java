package com.platform.recommendor.app.application.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String authToken;
    LoginResponse() {}
    public LoginResponse(String authToken) {
        this.authToken = authToken;
    }
}
