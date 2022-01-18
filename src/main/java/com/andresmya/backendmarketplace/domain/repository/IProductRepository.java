package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IProductRepository {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Optional<Product> getProductById(Integer id);
    Optional<Page<Product>> getProductsByCategoryId(Integer categoryId, Pageable pageable);
    Page<Product> getAllProducts(Pageable pageable);
    void deleteProductById(Integer id);
}
