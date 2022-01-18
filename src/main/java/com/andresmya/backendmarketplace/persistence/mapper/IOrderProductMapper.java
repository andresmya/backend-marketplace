package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.OrderProduct;
import com.andresmya.backendmarketplace.persistence.entity.OrderProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IProductMapper.class)
public interface IOrderProductMapper {
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);
    List<OrderProduct> toOrderProducts(List<OrderProductEntity> orderProductEntities);
}
