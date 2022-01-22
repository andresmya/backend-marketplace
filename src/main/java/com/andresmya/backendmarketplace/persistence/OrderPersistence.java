package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.Order;
import com.andresmya.backendmarketplace.domain.repository.IOrderRepository;
import com.andresmya.backendmarketplace.persistence.entity.OrderEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IOrderJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.IOrderPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderPersistence implements IOrderRepository {

    @Autowired
    private IOrderJpaRepository orderJpaRepository;

    @Autowired
    private IOrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = orderJpaRepository.save(orderPersistenceMapper.toOrderEntity(order));
        return orderPersistenceMapper.toOrder(orderEntity);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderJpaRepository.findById(id).map(orderEntity -> orderPersistenceMapper.toOrder(orderEntity));
    }

    @Override
    public Page<Order> getOrdersByCustomerId(Integer customerId, Pageable pageable) {
        return orderJpaRepository.findAllByCustomerId(customerId, pageable)
                .map(orderEntity -> orderPersistenceMapper.toOrder(orderEntity));
    }

    @Override
    public boolean existsById(Long id) {
        return orderJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderJpaRepository.deleteById(id);
    }
}
