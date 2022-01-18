package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.OrderTransaction;

import java.util.List;
import java.util.Optional;

public interface IOrderTransactionRepository {
    OrderTransaction createOrderTransaction(OrderTransaction orderTransaction);
    Optional<List<OrderTransaction>> getOrderTransactionsByOrderId(Long orderId);
}
