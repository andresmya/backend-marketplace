package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
