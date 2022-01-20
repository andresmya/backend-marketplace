package com.andresmya.backendmarketplace.domain.mapper;

import com.andresmya.backendmarketplace.domain.Category;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category toCategory(CreateCategoryRequest request);
}
