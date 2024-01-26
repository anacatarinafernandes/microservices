package com.anafernandes.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock {
    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "stock_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_id_sequence"

    )
    private Integer id;
    @Column(nullable = false, unique = true)
    private Integer bookId;
    @Column(nullable = false)
    private Integer stockAvailable;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;


}
