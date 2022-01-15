package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.OrderStatusTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderStatusTypeJpaRepository extends JpaRepository<OrderStatusTypeEntity, Integer> {
}