package com.anafernandes.stock.dto;

import com.anafernandes.stock.model.Stock;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDto toDto(Stock stock);

    Stock toEntity(StockDto stock);

    List<StockDto> toDtoList(List<Stock> stocks);
}
