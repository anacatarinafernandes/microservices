package com.anafernandes.stock.dto;

import com.anafernandes.stock.model.Stock;
import com.anafernandes.stock.model.Stock.StockBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-26T11:02:52+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class StockMapperImpl implements StockMapper {

    @Override
    public StockDto toDto(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockDto stockDto = new StockDto();

        stockDto.setBookId( stock.getBookId() );
        stockDto.setStockAvailable( stock.getStockAvailable() );
        stockDto.setDateCreated( stock.getDateCreated() );
        stockDto.setDateUpdated( stock.getDateUpdated() );

        return stockDto;
    }

    @Override
    public Stock toEntity(StockDto stock) {
        if ( stock == null ) {
            return null;
        }

        StockBuilder stock1 = Stock.builder();

        stock1.bookId( stock.getBookId() );
        stock1.stockAvailable( stock.getStockAvailable() );
        stock1.dateCreated( stock.getDateCreated() );
        stock1.dateUpdated( stock.getDateUpdated() );

        return stock1.build();
    }

    @Override
    public List<StockDto> toDtoList(List<Stock> stocks) {
        if ( stocks == null ) {
            return null;
        }

        List<StockDto> list = new ArrayList<StockDto>( stocks.size() );
        for ( Stock stock : stocks ) {
            list.add( toDto( stock ) );
        }

        return list;
    }
}
