package com.platform.recommendor.app.domain.ports.out;
import com.platform.recommendor.app.domain.model.UserModel;
import java.util.List;

public interface UserRepository {
    List<UserModel> getAllUsers();
    UserModel getUserById(Long id);
    UserModel saveUser(UserModel user);
    void deleteUser(Long id);
    UserModel getUserByUsername(String username);
    UserModel getUserByEmail(String email);
}
