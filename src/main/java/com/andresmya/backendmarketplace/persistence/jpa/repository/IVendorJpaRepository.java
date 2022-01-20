package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IVendorJpaRepository extends JpaRepository<VendorEntity, Integer> {
    Optional<VendorEntity> findByUserId(Integer userId);
}
