package com.anafernandes.stock.service;

import com.anafernandes.stock.model.Stock;
import com.anafernandes.stock.repository.StockRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;

@Service
public class StockNotificationJobService {

    private final StockRepository stockRepository;

    private static final Logger logger = LogManager.getLogger(StockNotificationJobService.class);

    public StockNotificationJobService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    @Recurring(id = "stock-notification-job", cron = "0 12 * * *")
    @Job(name = "Stock Notification job", retries = 10)
    public void sendNotificationJob() {

        var lowStockBooks = stockRepository.getBooksWithLowStock();

        for (Stock stock : lowStockBooks) {

            logger.warn("Book with id " + stock.getBookId() + " has low stock");

        }

    }
}
