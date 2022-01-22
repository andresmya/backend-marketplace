package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.OrderStatus;
import com.andresmya.backendmarketplace.domain.repository.IOrderStatusRepository;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IOrderStatusJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.IOrderStatusPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderStatusPersistence implements IOrderStatusRepository {

    @Autowired
    private IOrderStatusJpaRepository jpaRepository;

    @Autowired
    private IOrderStatusPersistenceMapper mapper;

    @Override
    public Optional<OrderStatus> getOrderStatusById(Integer id) {
        return jpaRepository.findById(id).map(orderStatusEntity -> mapper.toOrderStatus(orderStatusEntity));
    }

    @Override
    public Optional<OrderStatus> getOrderStatusByName(String name) {
        return jpaRepository.findByName(name).map(orderStatusEntity -> mapper.toOrderStatus(orderStatusEntity));
    }
}
