package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerAddressJpaRepository extends JpaRepository<CustomerAddressEntity, Integer> {
}