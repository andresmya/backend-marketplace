package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.Product;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateProductRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateProductRequest;
import com.andresmya.backendmarketplace.domain.mapper.IProductMapper;
import com.andresmya.backendmarketplace.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.exception.NotFoundException;
import com.andresmya.backendmarketplace.persistence.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductPersistence productRepository;

    @Autowired
    private IProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VendorService vendorService;

    public Page<Product> getAllProducts(Pageable pageable){
        return productRepository.getAllProducts(pageable);
    }

    public Product getProductById(Long id) throws Exception{
        return productRepository.getProductById(id).orElseThrow(() -> new NotFoundException("Product ID " + id));
    }

    public List<Product> getProductsByIdList(List<Long> idList) throws Exception{
        return idList.stream()
                .map(id -> productRepository.getProductById(id).orElse(null))
                .collect(Collectors.toList());
    }

    public Product createProduct(CreateProductRequest request) throws Exception {
        checkCreateArguments(request);
        request.setName(request.getName().toUpperCase());
        request.setDescription(request.getDescription().toUpperCase());
        Product newProduct = productMapper.toProduct(request);
        newProduct.setCategory(categoryService.getCategoryById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category ID " + request.getCategoryId())));
        newProduct.setVendor(vendorService.getVendorById(request.getVendorId()));
        return productRepository.createProduct(newProduct);
    }

    public Product updateProduct(Long id, UpdateProductRequest request) throws Exception{
        if (!productRepository.existsById(id)) throw new NotFoundException("Product ID " + id);
        checkUpdateArguments(request);
        Product product = getProductById(id);
        product.setDescription(request.getDescription().toUpperCase());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return productRepository.updateProduct(product);
    }

    public void deleteProductById(Long id) throws Exception{
        if (!productRepository.existsById(id)) throw new NotFoundException("Product ID " + id);
        productRepository.deleteProductById(id);
    }

    public Page<Product> getProductsByCategoryId(Pageable pageable, Integer categoryId){
        return productRepository.getProductsByCategoryId(categoryId, pageable);
    }

    public Page<Product> getProductsByVendorId(Pageable pageable, Integer categoryId){
        return productRepository.getProductsByVendorId(categoryId, pageable);
    }

    protected List<Product> updateAllProductsByList(List<Product> products){
        return productRepository.updateAllByList(products);
    }

    private void checkCreateArguments(CreateProductRequest request) throws InvalidArgumentException {
        if (
                request.getName() == null || request.getName().isEmpty() ||
                request.getDescription() == null || request.getDescription().isEmpty() ||
                request.getPrice() == null ||
                request.getStock() == null ||
                request.getCategoryId() == null ||
                request.getVendorId() == null

        ) throw new InvalidArgumentException("Product properties");
    }

    private void checkUpdateArguments(UpdateProductRequest request) throws InvalidArgumentException {
        if (
                request.getDescription() == null || request.getDescription().isEmpty() ||
                request.getPrice() == null ||
                request.getStock() == null
        ) throw new InvalidArgumentException("Product properties");
    }


}
