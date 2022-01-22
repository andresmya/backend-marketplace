package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long id);
    Page<Order> getOrdersByCustomerId(Integer customerId, Pageable pageable);
    boolean existsById(Long id);
    void deleteById(Long id);
}
