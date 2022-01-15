package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}