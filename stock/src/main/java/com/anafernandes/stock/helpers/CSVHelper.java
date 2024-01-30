package com.anafernandes.stock.helpers;

import com.anafernandes.stock.model.Stock;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Id", "BookId", "StockAvailable"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Stock> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Stock> stocks = new ArrayList<Stock>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Stock stock = new Stock(
                        Integer.parseInt(csvRecord.get("Id")),
                        Integer.parseInt(csvRecord.get("BookId")),
                        Integer.parseInt(csvRecord.get("StockAvailable")),
                        LocalDateTime.now(),
                        LocalDateTime.now()

                );

                stocks.add(stock);
            }

            return stocks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}