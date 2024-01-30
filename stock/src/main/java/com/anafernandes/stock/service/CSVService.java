package com.anafernandes.stock.service;

import com.anafernandes.stock.helpers.CSVHelper;
import com.anafernandes.stock.model.Stock;
import com.anafernandes.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    StockRepository stockRepository;

    public void save(MultipartFile file) {
        try {
            List<Stock> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
            stockRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Stock> getAllTutorials() {
        return stockRepository.findAll();
    }
}