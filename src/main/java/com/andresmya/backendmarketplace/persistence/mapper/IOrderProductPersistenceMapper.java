package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.OrderProduct;
import com.andresmya.backendmarketplace.persistence.entity.OrderProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IProductPersistenceMapper.class)
public interface IOrderProductPersistenceMapper {
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
    List<OrderProduct> toOrderProducts(List<OrderProductEntity> orderProductEntities);
}
