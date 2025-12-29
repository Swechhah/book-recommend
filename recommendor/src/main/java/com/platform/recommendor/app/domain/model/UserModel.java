package com.platform.recommendor.app.domain.model;

import java.util.Arrays;


public class UserModel {
    public enum UserRole{
        ADMIN("admin"),
        USER("user");
        private String role;
        UserRole(String role){
            this.role=role;
        }
        public String getRole(){
            return role;
        }

        public static UserRole fromString(String role) {
            return Arrays.stream(UserRole.values())
                    .filter(s -> s.role.equalsIgnoreCase(role))
                    .findFirst()
                    .orElse(USER);
        }
    }
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private UserRole role;

    public UserModel(String firstName, Long id, String lastName, String email, String username, String password, UserRole role) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserModel(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
