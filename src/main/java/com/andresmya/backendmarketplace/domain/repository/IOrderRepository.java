package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {
    Order createOrder(Order order);
    Optional<Order> getOrderById(Long id);
    Optional<List<Order>> getOrdersByCustomerId(Integer customerId);
}
