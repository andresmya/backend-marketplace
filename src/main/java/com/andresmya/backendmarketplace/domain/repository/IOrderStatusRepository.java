package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.OrderStatus;

import java.util.Optional;

public interface IOrderStatusRepository {
    Optional<OrderStatus> getOrderStatusById(Integer id);
    Optional<OrderStatus> getOrderStatusByName(String name);
}
