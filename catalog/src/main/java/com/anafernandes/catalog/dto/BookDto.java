package com.anafernandes.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String title;
    private String originalTitle;
    private Integer isbn;
    private String language;
    private Integer stockAvailable;
    private Double price;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private Set<AuthorDto> authors = new HashSet<>();

    private CategoryDto category;
}
