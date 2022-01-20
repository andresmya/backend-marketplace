package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.Customer;
import com.andresmya.backendmarketplace.domain.repository.ICustomerRepository;
import com.andresmya.backendmarketplace.persistence.entity.CustomerEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.ICustomerJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.ICustomerPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class CustomerPersistence implements ICustomerRepository {

    @Autowired
    private ICustomerJpaRepository customerJpaRepository;

    @Autowired
    private ICustomerPersistenceMapper customerPersistenceMapper;

    @Override
    public Customer createCustomer(Customer customer) {
        CustomerEntity customerEntity = customerJpaRepository.save(customerPersistenceMapper.toCustomerEntity(customer));
        customerEntity.setCreatedAt(new Date());
        customerEntity.setUpdatedAt(new Date());
        return customerPersistenceMapper.toCustomer(customerEntity);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        CustomerEntity customerEntity = customerJpaRepository.save(customerPersistenceMapper.toCustomerEntity(customer));
        return customerPersistenceMapper.toCustomer(customerEntity);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerJpaRepository.findById(id).map(customerEntity -> customerPersistenceMapper.toCustomer(customerEntity));
    }

    @Override
    public Optional<Customer> getCustomerByUserId(Integer userId) {
        return customerJpaRepository.findByUserId(userId).map(customerEntity -> customerPersistenceMapper.toCustomer(customerEntity));
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerJpaRepository.findAll(pageable).map(customerEntity -> customerPersistenceMapper.toCustomer(customerEntity));
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customerJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return customerJpaRepository.existsById(id);
    }
}
