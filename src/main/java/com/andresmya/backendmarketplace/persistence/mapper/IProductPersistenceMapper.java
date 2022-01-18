package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.Product;
import com.andresmya.backendmarketplace.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ICategoryPersistenceMapper.class, IVendorPersistenceMapper.class})
public interface IProductPersistenceMapper {
    Product toProduct(ProductEntity productEntity);
    List<Product> toProducts(List<ProductEntity> productEntities);
}
