package com.anafernandes.catalog.dto;

import com.anafernandes.catalog.dto.CategoryDto.CategoryDtoBuilder;
import com.anafernandes.catalog.model.Category;
import com.anafernandes.catalog.model.Category.CategoryBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T11:02:55+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.name( category.getName() );

        return categoryDto.build();
    }

    @Override
    public Category toEntity(CategoryDto category) {
        if ( category == null ) {
            return null;
        }

        CategoryBuilder category1 = Category.builder();

        category1.name( category.getName() );

        return category1.build();
    }
}
