package com.andresmya.backendmarketplace.persistence.jpa.repository;

import com.andresmya.backendmarketplace.persistence.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICustomerAddressJpaRepository extends JpaRepository<CustomerAddressEntity, Integer> {
    Optional<List<CustomerAddressEntity>> findAllByCustomerId(Integer customerId);
}