package com.anafernandes.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Integer id;
    private String name;
    private String fullName;
    private LocalDate birthDate;
    private String birthPlace;
    private LocalDate deathDate;
    private String deathPlace;
    private String about;

}
