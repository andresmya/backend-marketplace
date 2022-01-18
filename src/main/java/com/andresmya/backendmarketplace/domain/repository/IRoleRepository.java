package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Role;

import java.util.Optional;

public interface IRoleRepository {
    Optional<Role> getRoleById(Integer id);
}
