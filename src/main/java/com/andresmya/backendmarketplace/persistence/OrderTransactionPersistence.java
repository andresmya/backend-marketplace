package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.OrderTransaction;
import com.andresmya.backendmarketplace.domain.repository.IOrderTransactionRepository;
import com.andresmya.backendmarketplace.persistence.entity.OrderTransactionEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IOrderTransactionJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.IOrderTransactionPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderTransactionPersistence implements IOrderTransactionRepository {

    @Autowired
    private IOrderTransactionJpaRepository jpaRepository;

    @Autowired
    private IOrderTransactionPersistenceMapper mapper;

    @Override
    public OrderTransaction createOrderTransaction(OrderTransaction orderTransaction) {
        OrderTransactionEntity entity = jpaRepository.save(mapper.toOrderTransactionEntity(orderTransaction));
        return mapper.toOrderTransaction(entity);
    }

    @Override
    public Optional<List<OrderTransaction>> getOrderTransactionsByOrderId(Long orderId) {
        return jpaRepository.findAllByOrderId(orderId).map(orderEntities -> mapper.toOrdersTransactions(orderEntities));
    }
}
