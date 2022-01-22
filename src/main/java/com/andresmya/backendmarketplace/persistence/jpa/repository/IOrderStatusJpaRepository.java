package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrderStatusJpaRepository extends JpaRepository<OrderStatusEntity, Integer> {
    Optional<OrderStatusEntity> findByName(String name);
}