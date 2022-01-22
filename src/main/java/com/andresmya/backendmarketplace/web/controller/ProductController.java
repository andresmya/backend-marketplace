package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.Product;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateProductRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateProductRequest;
import com.andresmya.backendmarketplace.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("page") int page,
                                                        @RequestParam("size")int size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(productService.getAllProducts(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) throws Exception {
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") Long id, @RequestBody UpdateProductRequest request) throws Exception{
        return new ResponseEntity<>(productService.updateProduct(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) throws Exception {
        productService.deleteProductById(id);
    }

    protected Page<Product> getProductsByCategoryId(Pageable pageable, Integer categoryId){
        return productService.getProductsByCategoryId(pageable, categoryId);
    }

    protected Page<Product> getProductsByVendorId(Pageable pageable, Integer vendorId){
        return productService.getProductsByVendorId(pageable, vendorId);
    }

}
