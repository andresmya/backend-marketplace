package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrivilegeJpaRepository extends JpaRepository<PrivilegeEntity, Integer> {
}