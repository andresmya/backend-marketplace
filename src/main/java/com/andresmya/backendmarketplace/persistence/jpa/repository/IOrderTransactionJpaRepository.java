package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.OrderTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderTransactionJpaRepository extends JpaRepository<OrderTransactionEntity, Long> {
}