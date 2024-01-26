package com.anafernandes.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    private Integer bookId;
    private Integer stockAvailable;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
