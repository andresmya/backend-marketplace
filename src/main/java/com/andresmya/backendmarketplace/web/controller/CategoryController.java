package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.Category;
import com.andresmya.backendmarketplace.domain.Product;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCategoryRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateCategoryRequest;
import com.andresmya.backendmarketplace.domain.service.CategoryService;
import com.andresmya.backendmarketplace.domain.service.RoleService;
import com.andresmya.backendmarketplace.domain.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductController productController;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest request) throws Exception {
        return new ResponseEntity<>(categoryService.createCategory(request), HttpStatus.CREATED);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN)
    @PatchMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody UpdateCategoryRequest request) throws NotFoundException, InvalidArgumentException {
        return new ResponseEntity<>(categoryService.updateCategory(id, request), HttpStatus.OK);
    }

    @PreAuthorize(RoleService.HAS_ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable("id") Integer id) throws NotFoundException {
        categoryService.deleteCategoryById(id);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<Product>> getProductsByCategoryId(@RequestParam("page") int page,
                                                                @RequestParam("size")int size,
                                                                 @PathVariable("id") Integer id){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(productController.getProductsByCategoryId(pageable, id), HttpStatus.OK);
    }

}
