package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {
        Optional<Category> getCategoryById(Integer id);
        Category createCategory(Category category);
        Category updateCategory(Category category);
        List<Category> getAllCategories();
        void deleteCategoryById(Integer id);
        boolean existsById(Integer id);
}
