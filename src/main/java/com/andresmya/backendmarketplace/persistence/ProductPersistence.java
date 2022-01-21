package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.Product;
import com.andresmya.backendmarketplace.domain.repository.IProductRepository;
import com.andresmya.backendmarketplace.persistence.entity.ProductEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IProductJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.IProductPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class ProductPersistence implements IProductRepository {

    @Autowired
    private IProductJpaRepository productJpaRepository;

    @Autowired
    private IProductPersistenceMapper productMapper;

    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = productJpaRepository.save(productMapper.toProductEntity(product));
        productEntity.setCreatedAt(new Date());
        productEntity.setUpdatedAt(new Date());
        return productMapper.toProduct(productEntity);
    }

    @Override
    public Product updateProduct(Product product) {
        ProductEntity productEntity = productJpaRepository.save(productMapper.toProductEntity(product));
        return productMapper.toProduct(productEntity);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productJpaRepository.findById(id).map(productEntity -> productMapper.toProduct(productEntity));

    }

    @Override
    public Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable) {
        return productJpaRepository.findByCategoryId(categoryId, pageable)
                .map(productEntity -> productMapper.toProduct(productEntity));
    }

    @Override
    public Page<Product> getProductsByVendorId(Integer vendorId, Pageable pageable) {
        return productJpaRepository.findByVendorId(vendorId, pageable)
                .map(productEntity -> productMapper.toProduct(productEntity));
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        Page<ProductEntity> productEntityPage = productJpaRepository.findAll(pageable);
        return productEntityPage.map(productEntity -> productMapper.toProduct(productEntity));
    }

    @Override
    public void deleteProductById(Long id) {
        productJpaRepository.deleteById(id);
    }

}
