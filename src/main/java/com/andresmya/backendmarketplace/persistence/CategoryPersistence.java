package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.Category;
import com.andresmya.backendmarketplace.domain.repository.ICategoryRepository;
import com.andresmya.backendmarketplace.persistence.entity.CategoryEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.ICategoryJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.ICategoryPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryPersistence implements ICategoryRepository {

    @Autowired
    private ICategoryJpaRepository categoryJpaRepository;

    @Autowired
    private ICategoryPersistenceMapper categoryPersistenceMapper;

    @Override
    public Optional<Category> getCategoryById(Integer id) {
        return categoryJpaRepository.findById(id).map(categoryEntity -> categoryPersistenceMapper.toCategory(categoryEntity));
    }

    @Override
    public Category createCategory(Category category) {
        CategoryEntity categoryEntity = categoryJpaRepository.save(categoryPersistenceMapper.toCategoryEntity(category));
        return categoryPersistenceMapper.toCategory(categoryEntity);
    }

    @Override
    public Category updateCategory(Category category) {
        CategoryEntity categoryEntity = categoryJpaRepository.save(categoryPersistenceMapper.toCategoryEntity(category));
        return categoryPersistenceMapper.toCategory(categoryEntity);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistenceMapper.toCategories(categoryJpaRepository.findAll());
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return categoryJpaRepository.existsById(id);
    }
}
