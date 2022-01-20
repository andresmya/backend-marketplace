package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.Role;
import com.andresmya.backendmarketplace.domain.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository roleRepository;

    public static final String HAS_ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
    public static final String HAS_ROLE_VENDOR = "hasRole('ROLE_VENDOR')";
    public static final String HAS_ROLE_CUSTOMER = "hasRole('ROLE_CUSTOMER')";

    protected Optional<Role> getRoleById(Integer id){
        return roleRepository.getRoleById(id);
    }


}
