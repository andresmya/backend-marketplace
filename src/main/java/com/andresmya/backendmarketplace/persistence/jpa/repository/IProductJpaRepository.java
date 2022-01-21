package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findByCategoryId(Integer id, Pageable pageable);
    Page<ProductEntity> findByVendorId(Integer id, Pageable pageable);
}
