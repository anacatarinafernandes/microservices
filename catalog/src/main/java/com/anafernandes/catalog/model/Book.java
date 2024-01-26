package com.anafernandes.catalog.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_id_sequence",
            sequenceName = "book_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_id_sequence"

    )
    private Integer id;
    @Column(nullable = false, unique = true)
    private String title;
    private String originalTitle;
    @Column(nullable = false, unique = true)
    private Integer isbn;
    @Column(nullable = false)
    private String language;
    @Column(nullable = false)
    private Integer stockAvailable;
    @Column(nullable = false)
    private Double price;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "bookAuthors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    Set<Author> authors = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    Category category;

}