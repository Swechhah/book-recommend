package com.platform.recommendor.app.infrastucture.adapters;

import com.platform.recommendor.app.domain.model.UserModel;
import com.platform.recommendor.app.infrastucture.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
    UserEntity toUserEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userModel.getId());
        userEntity.setUsername(userModel.getUsername());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setFirstName(userModel.getFirstName());
        userEntity.setLastName(userModel.getLastName());
        return userEntity;
    }
    UserModel toUserModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setPassword(userEntity.getPassword());
        userModel.setEmail(userEntity.getEmail());
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        return userModel;
    }
}
