package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityJpaRepository extends JpaRepository<CityEntity, Integer> {
}