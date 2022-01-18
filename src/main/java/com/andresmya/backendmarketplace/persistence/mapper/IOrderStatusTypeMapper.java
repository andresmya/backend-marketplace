package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.OrderStatusType;
import com.andresmya.backendmarketplace.persistence.entity.OrderStatusTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderStatusTypeMapper {
    OrderStatusType toOrderStatusType(OrderStatusTypeEntity orderStatusTypeEntity);
    List<OrderStatusType> toOrderStatusTypes(List<OrderStatusTypeEntity> orderStatusTypeEntities);
}
