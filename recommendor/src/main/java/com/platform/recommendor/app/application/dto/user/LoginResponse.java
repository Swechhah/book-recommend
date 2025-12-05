package com.platform.recommendor.app.application.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String accessToken;
    LoginResponse() {}
    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
