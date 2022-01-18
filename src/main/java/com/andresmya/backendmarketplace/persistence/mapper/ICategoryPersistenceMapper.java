package com.andresmya.backendmarketplace.persistence.mapper;

import com.andresmya.backendmarketplace.domain.Category;
import com.andresmya.backendmarketplace.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryPersistenceMapper {
    Category toCategory(CategoryEntity categoryEntity);
    List<Category> toCategories(List<CategoryEntity> categoryEntities);
}
