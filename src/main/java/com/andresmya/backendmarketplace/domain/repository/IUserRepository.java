package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserRepository {
    User createUser(User user);
    User updateUser(User user);
    Optional<User> getUserById(Integer id);
    Optional<User> getUserByEmail(String email);
    Page<User> getAllUsers(Pageable pageable);
    void deleteUserById(Integer id);
}
