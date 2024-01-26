package com.anafernandes.stock.service;

import com.anafernandes.stock.controller.StockRequest;
import com.anafernandes.stock.dto.StockDto;
import com.anafernandes.stock.dto.StockMapper;
import com.anafernandes.stock.model.Stock;
import com.anafernandes.stock.repository.StockRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final RabbitTemplate rabbitTemplate;

    public StockService(StockRepository stockRepository, StockMapper stockMapper, RabbitTemplate rabbitTemplate) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
        this.rabbitTemplate = rabbitTemplate;
    }


    public StockDto getBookStock(Integer bookId) {

        return stockMapper.toDto(stockRepository.getStockByBook(bookId));

    }

    public StockDto addBookStock(StockRequest stockRequest) {
        Stock stock = Stock.builder()
                .bookId(stockRequest.bookId())
                .stockAvailable(stockRequest.stockAvailable())
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();

        Stock stockCreated = this.stockRepository.save(stock);
        return stockMapper.toDto(stockCreated);
    }

    public StockDto updateBookStock(StockRequest stockRequest) {

        Stock stock = stockRepository.getStockByBook(stockRequest.bookId());

        stock.setStockAvailable(stockRequest.stockAvailable());

        stock.setDateUpdated(LocalDateTime.now());

        stockRepository.saveAndFlush(stock);

        rabbitTemplate.convertAndSend(exchange, routingKey, stock);

        return stockMapper.toDto(stock);
    }

    public List<StockDto> getBooksOutOfStock() {

        return stockMapper.toDtoList(stockRepository.getBooksOutOfStock());
    }
}
