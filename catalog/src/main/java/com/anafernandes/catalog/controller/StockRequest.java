package com.anafernandes.catalog.controller;

public record StockRequest(Integer bookId,
                           Integer stockAvailable) {
}
