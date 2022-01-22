package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IProductRepository {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    List<Product> updateAllByList(List<Product> products);
    Optional<Product> getProductById(Long id);
    Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable);
    Page<Product> getProductsByVendorId(Integer vendorId, Pageable pageable);
    Page<Product> getAllProducts(Pageable pageable);
    void deleteProductById(Long id);
    boolean existsById(Long id);
}
