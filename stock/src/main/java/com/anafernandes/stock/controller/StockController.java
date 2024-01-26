package com.anafernandes.stock.controller;

import com.anafernandes.stock.dto.StockDto;
import com.anafernandes.stock.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(path = "{bookId}")
    public ResponseEntity<StockDto> getBookStock(@PathVariable("bookId") Integer bookId) {

        StockDto bookStock = stockService.getBookStock(bookId);
        return new ResponseEntity<>(bookStock, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StockDto> addBookStock(@RequestBody StockRequest stockRequest) {

        StockDto bookStock = stockService.addBookStock(stockRequest);
        return new ResponseEntity<>(bookStock, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<StockDto> updateBookStock(@RequestBody StockRequest stockRequest) {

        StockDto bookStock = stockService.updateBookStock(stockRequest);
        return new ResponseEntity<>(bookStock, HttpStatus.OK);

    }

    @GetMapping(path = "/outOfStock")
    public ResponseEntity<List<StockDto>> getBooksOutOfStock() {

        List<StockDto> booksOutOfStock = stockService.getBooksOutOfStock();
        return new ResponseEntity<>(booksOutOfStock, HttpStatus.OK);
    }
}
