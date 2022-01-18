package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICustomerRepository {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Optional<Customer> getCustomerById(Integer id);
    Optional<Customer> getCustomerByEmail(String email);
    Page<Customer> getAllCustomers(Pageable pageable);
    void deleteCustomerById(Integer id);
}
