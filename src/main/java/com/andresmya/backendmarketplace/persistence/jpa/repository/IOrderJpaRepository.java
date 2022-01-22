package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderJpaRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerId(Integer customerId, Pageable pageable);
}