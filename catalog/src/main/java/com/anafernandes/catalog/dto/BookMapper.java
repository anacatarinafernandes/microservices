package com.anafernandes.catalog.dto;

import com.anafernandes.catalog.model.Book;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto book);

    List<BookDto> toDtoList(List<Book> books);

}
