package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderProductJpaRepository extends JpaRepository<OrderProductEntity, Long> {
}