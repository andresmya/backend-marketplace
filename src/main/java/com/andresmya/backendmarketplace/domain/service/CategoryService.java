package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.Category;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCategoryRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdateCategoryRequest;
import com.andresmya.backendmarketplace.domain.mapper.ICategoryMapper;
import com.andresmya.backendmarketplace.domain.repository.ICategoryRepository;
import com.andresmya.backendmarketplace.domain.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ICategoryMapper categoryMapper;

    public Category createCategory(CreateCategoryRequest request) throws InvalidArgumentException {
        checkIfArgumentsAreValid(request.getName());
        request.setName(request.getName().toUpperCase());
        return categoryRepository.createCategory(categoryMapper.toCategory(request));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public Category updateCategory(Integer id, UpdateCategoryRequest request) throws NotFoundException, InvalidArgumentException {
        checkIfArgumentsAreValid(request.getName());
        Category category = getCategoryById(id).orElseThrow(() -> new NotFoundException("Category ID " + id));
        category.setName(request.getName().toUpperCase());
        return categoryRepository.updateCategory(category);
    }

    public void deleteCategoryById(Integer id) throws NotFoundException {
        if (!categoryRepository.existsById(id)) throw new NotFoundException("Category ID " + id);
        categoryRepository.deleteCategoryById(id);
    }

    protected Optional<Category> getCategoryById(Integer id){
        return categoryRepository.getCategoryById(id);
    }

    private void checkIfArgumentsAreValid(String name) throws InvalidArgumentException {
        if (name == null) throw new InvalidArgumentException("Category name");
    }
}
