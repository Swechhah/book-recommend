package com.platform.recommendor.app.infrastucture.adapters;

import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.BookRecommendorRepository;
import com.platform.recommendor.app.infrastucture.entities.UserEntity;
import com.platform.recommendor.app.infrastucture.repositories.UserJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRecommendorRepositoryAdapter implements BookRecommendorRepository {
    private final UserJpaRepository userJpaRepository;
    private final EntityMapper entityMapper;

    BookRecommendorRepositoryAdapter(UserJpaRepository userJpaRepository,  EntityMapper entityMapper) {
        this.userJpaRepository = userJpaRepository;
        this.entityMapper = entityMapper;
    }
    @Override
    public Optional<List<UserModel>> getAllUsers() {
        return Optional.of(userJpaRepository.findAll().stream().map(entityMapper::toUserModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userJpaRepository.findById(id).map(entityMapper::toUserModel);
    }

    @Override
    public UserModel saveUser(UserModel user) {
        UserEntity  userEntity = entityMapper.toUserEntity(user);
        UserEntity userEntitySaved = userJpaRepository.save(userEntity);
        return entityMapper.toUserModel(userEntitySaved);
    }

    @Override
    public void deleteUser(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public Optional<UserModel> getUserByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(entityMapper::toUserModel);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(entityMapper::toUserModel);
    }
}
