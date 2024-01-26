package com.anafernandes.catalog.dto;

import com.anafernandes.catalog.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto category);
}
