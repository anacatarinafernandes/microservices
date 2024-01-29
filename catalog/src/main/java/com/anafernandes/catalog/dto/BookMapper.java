package com.anafernandes.catalog.dto;

import com.anafernandes.catalog.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "availability", source = "availability")
    BookDto toDto(Book book);

    @Mapping(target = "availability", source = "availability")
    Book toEntity(BookDto book);

    List<BookDto> toDtoList(List<Book> books);

}
