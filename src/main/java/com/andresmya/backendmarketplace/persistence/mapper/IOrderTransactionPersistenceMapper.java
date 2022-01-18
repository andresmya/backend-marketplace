package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.OrderTransaction;
import com.andresmya.backendmarketplace.persistence.entity.OrderTransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IOrderStatusPersistenceMapper.class)
public interface IOrderTransactionPersistenceMapper {
    OrderTransaction toOrderTransaction(OrderTransactionEntity orderTransactionEntity);
    List<OrderTransaction> toOrdersTransactions(List<OrderTransactionEntity> orderTransactionEntities);
}
