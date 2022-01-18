package com.andresmya.backendmarketplace.domain.repository;

import com.andresmya.backendmarketplace.domain.Category;

import java.util.List;

public interface ICategoryRepository {
        Category createCategory(Category category);
        Category updateCategory(Category category);
        List<Category> getAllCategories();
        void deleteCategoryById(Integer id);
}
