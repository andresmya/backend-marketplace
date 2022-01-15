package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendorJpaRepository extends JpaRepository<VendorEntity, Integer> {
}
