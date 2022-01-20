package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IRolePersistenceMapper.class)
public interface IUserPersistenceMapper {
    User toUser(UserEntity userEntity);
    List<User> toUsers(List<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);
}
