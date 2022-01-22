package com.andresmya.backendmarketplace.domain.mapper;

import com.andresmya.backendmarketplace.domain.Order;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Order toOrder(CreateOrderRequest request);
}
