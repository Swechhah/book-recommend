package com.platform.recommendor.app.infrastructure.entities;

import com.platform.recommendor.app.domain.model.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private int age;
    @Column
    private String location;
    @Column
    private UserModel.UserRole role;

}
