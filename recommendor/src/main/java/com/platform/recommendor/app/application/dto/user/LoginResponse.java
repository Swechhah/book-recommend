package com.platform.recommendor.app.application.dto.user;

public class LoginResponse {
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {}

    LoginResponse() {}
    public LoginResponse(String authToken) {
        this.authToken = authToken;
    }
}
