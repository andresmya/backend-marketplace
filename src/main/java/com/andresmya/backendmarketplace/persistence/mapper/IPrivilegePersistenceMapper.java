package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.Privilege;
import com.andresmya.backendmarketplace.persistence.entity.PrivilegeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPrivilegePersistenceMapper {
    Privilege toPrivilege(PrivilegeEntity privilegeEntity);
    List<Privilege> toPrivileges(List<PrivilegeEntity> privilegeEntities);
}
