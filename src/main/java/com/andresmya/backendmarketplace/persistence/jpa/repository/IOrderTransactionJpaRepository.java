package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.OrderTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderTransactionJpaRepository extends JpaRepository<OrderTransactionEntity, Long> {
    Optional<List<OrderTransactionEntity>> findAllByOrderId(Long orderId);
}