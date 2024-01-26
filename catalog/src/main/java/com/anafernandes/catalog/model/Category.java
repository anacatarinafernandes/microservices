package com.anafernandes.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_id_sequence",
            sequenceName = "category_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_id_sequence"

    )
    private Integer id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Book> books;
}
