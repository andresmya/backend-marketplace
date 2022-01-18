package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.Customer;
import com.andresmya.backendmarketplace.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICustomerPersistenceMapper {
    Customer toCustomer(CustomerEntity customerEntity);
    List<Customer> toCustomers(List<CustomerEntity> customerEntities);
}
