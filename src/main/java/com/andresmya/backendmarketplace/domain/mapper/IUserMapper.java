package com.andresmya.backendmarketplace.domain.mapper;

import com.andresmya.backendmarketplace.domain.Role;
import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.dto.request.CreateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "updated_at", ignore = true),
            @Mapping(target = "created_at", ignore = true)
    })
    User toUser(CreateUserRequest request, Role role);
}
