package com.anafernandes.catalog.dto;

import com.anafernandes.catalog.model.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    Author toEntity(AuthorDto author);

    List<AuthorDto> toDtoList(List<Author> authors);

    List<Author> toEntityList(List<AuthorDto> authors);
}
