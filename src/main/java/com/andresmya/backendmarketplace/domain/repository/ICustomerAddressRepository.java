package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.CustomerAddress;

import java.util.List;
import java.util.Optional;

public interface ICustomerAddressRepository {
    CustomerAddress createCustomerAddress(CustomerAddress customerAddress);
    Optional<CustomerAddress> getCustomerAddressById(Integer id);
    Optional<List<CustomerAddress>> getCustomerAddressesByCustomerId(Integer customerId);
    void deleteCustomerAddressById(Integer id);
}
