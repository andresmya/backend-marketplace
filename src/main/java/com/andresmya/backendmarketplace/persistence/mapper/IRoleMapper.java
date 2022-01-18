package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.Role;
import com.andresmya.backendmarketplace.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IPrivilegeMapper.class})
public interface IRoleMapper {
    Role toRole(RoleEntity roleEntity);
    List<Role> toRoles(List<RoleEntity> roleEntities);
}
