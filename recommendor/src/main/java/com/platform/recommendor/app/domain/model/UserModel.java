package com.platform.recommendor.app.domain.model;

import lombok.*;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
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
}
