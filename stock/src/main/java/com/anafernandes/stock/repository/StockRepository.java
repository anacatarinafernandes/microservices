package com.anafernandes.stock.repository;

import com.anafernandes.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query("SELECT s FROM Stock s WHERE s.bookId=?1")
    Stock getStockByBook(Integer bookId);

    @Query("SELECT s FROM Stock s WHERE s.stockAvailable = 0")
    List<Stock> getBooksOutOfStock();

}
