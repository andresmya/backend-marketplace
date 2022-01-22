package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.Role;
import com.andresmya.backendmarketplace.domain.repository.IRoleRepository;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IRoleJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.IRolePersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RolePersistence implements IRoleRepository {

    @Autowired
    private IRoleJpaRepository roleJpaRepository;

    @Autowired
    private IRolePersistenceMapper rolePersistenceMapper;

    @Override
    public Optional<Role> getRoleById(Integer id) {
        return roleJpaRepository.findById(id).map(roleEntity -> rolePersistenceMapper.toRole(roleEntity));
    }
}
