package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IRoleMapper.class)
public interface IUserMapper {
    User toUser(UserEntity userEntity);
    List<User> toUsers(List<UserEntity> userEntities);
}
