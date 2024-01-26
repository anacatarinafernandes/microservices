package com.anafernandes.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @SequenceGenerator(
            name = "author_id_sequence",
            sequenceName = "author_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_id_sequence"

    )
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    private String fullName;
    private LocalDate birthDate;
    private String birthPlace;
    private LocalDate deathDate;
    private String deathPlace;
    private String about;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    Set<Book> books = new HashSet<>();
    ;
}
