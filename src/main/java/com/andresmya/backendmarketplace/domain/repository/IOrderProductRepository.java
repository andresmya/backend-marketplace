package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.OrderProduct;

import java.util.List;

public interface IOrderProductRepository {
    List<OrderProduct> getProductsByOrderId(Long orderId);
}
