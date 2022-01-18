package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.Order;
import com.andresmya.backendmarketplace.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ICustomerAddressMapper.class)
public interface IOrderMapper {
    Order toOrder(OrderEntity orderEntity);
    List<Order> toOrders(List<OrderEntity> orderEntities);
}
