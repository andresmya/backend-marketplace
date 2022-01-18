package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.OrderStatus;
import com.andresmya.backendmarketplace.persistence.entity.OrderStatusEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IOrderStatusTypeMapper.class)
public interface IOrderStatusMapper {
    OrderStatus toOrderStatus(OrderStatusEntity orderStatusEntity);
    List<OrderStatus> toOrderStatus(List<OrderStatusEntity> orderStatusEntities);
}
