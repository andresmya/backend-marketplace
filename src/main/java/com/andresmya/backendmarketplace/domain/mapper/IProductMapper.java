package com.andresmya.backendmarketplace.domain.mapper;

import com.andresmya.backendmarketplace.domain.Product;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IProductMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "category", ignore = true),
            @Mapping(target = "vendor", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    Product toProduct(CreateProductRequest request);
}
