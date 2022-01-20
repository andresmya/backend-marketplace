package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByUserId(Integer id);
}