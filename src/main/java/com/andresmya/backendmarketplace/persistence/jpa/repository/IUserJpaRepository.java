package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserJpaRepository extends JpaRepository<UserEntity, Integer> {
}