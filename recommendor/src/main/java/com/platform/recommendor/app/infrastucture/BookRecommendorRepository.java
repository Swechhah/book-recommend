package com.platform.recommendor.app.infrastucture;

import com.platform.recommendor.app.domain.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRecommendorRepository {

    Optional<List<UserModel>> getAllUsers();
    Optional<UserModel> getUserById(Long id);
    UserModel saveUser(UserModel user);
    void deleteUser(Long id);
    Optional<UserModel> getUserByUsername(String username);
    Optional<UserModel> getUserByEmail(String email);
}
