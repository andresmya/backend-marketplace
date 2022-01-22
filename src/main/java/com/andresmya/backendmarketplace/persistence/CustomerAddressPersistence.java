package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.CustomerAddress;
import com.andresmya.backendmarketplace.domain.repository.ICustomerAddressRepository;
import com.andresmya.backendmarketplace.persistence.entity.CustomerAddressEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.ICustomerAddressJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.ICustomerAddressPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerAddressPersistence implements ICustomerAddressRepository {

    @Autowired
    private ICustomerAddressJpaRepository jpaRepository;

    @Autowired
    private ICustomerAddressPersistenceMapper mapper;

    @Override
    public CustomerAddress createCustomerAddress(CustomerAddress customerAddress) {
        CustomerAddressEntity newCustomerAddressEntity = jpaRepository
                .save(mapper.toCustomerAddressEntity(customerAddress));
        return mapper.toCustomerAddress(newCustomerAddressEntity);
    }

    @Override
    public CustomerAddress updateCustomerAddress(CustomerAddress customerAddress) {
        CustomerAddressEntity customerAddressEntity = jpaRepository
                .save(mapper.toCustomerAddressEntity(customerAddress));
        return mapper.toCustomerAddress(customerAddressEntity);
    }

    @Override
    public Optional<CustomerAddress> getCustomerAddressById(Integer id) {
        return jpaRepository.findById(id)
                .map(customerAddressEntity -> mapper.toCustomerAddress(customerAddressEntity));
    }

    @Override
    public Optional<List<CustomerAddress>> getCustomerAddressesByCustomerId(Integer customerId) {
        return jpaRepository.findAllByCustomerId(customerId)
                .map(customerAddressEntities -> mapper.toCustomerAddresses(customerAddressEntities));
    }

    @Override
    public void deleteCustomerAddressById(Integer id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return jpaRepository.existsById(id);
    }
}
