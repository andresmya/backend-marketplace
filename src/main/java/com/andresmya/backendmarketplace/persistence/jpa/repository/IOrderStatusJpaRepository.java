package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderStatusJpaRepository extends JpaRepository<OrderStatusEntity, Integer> {
}