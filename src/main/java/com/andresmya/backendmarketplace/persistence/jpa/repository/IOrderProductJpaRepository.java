package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.domain.OrderProduct;
import com.andresmya.backendmarketplace.persistence.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderProductJpaRepository extends JpaRepository<OrderProductEntity, Long> {
    List<OrderProduct> findAllByOrderId(Long orderId);
}