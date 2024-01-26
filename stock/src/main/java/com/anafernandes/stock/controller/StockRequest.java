package com.anafernandes.stock.controller;

public record StockRequest(Integer bookId,
                           Integer stockAvailable) {
}
