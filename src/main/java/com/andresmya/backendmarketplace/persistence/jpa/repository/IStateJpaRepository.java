package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStateJpaRepository extends JpaRepository<StateEntity, Integer> {
}