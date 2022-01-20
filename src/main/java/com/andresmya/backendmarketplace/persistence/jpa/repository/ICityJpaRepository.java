package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICityJpaRepository extends JpaRepository<CityEntity, Integer> {
    List<CityEntity> findByStateId(Integer stateId);
}