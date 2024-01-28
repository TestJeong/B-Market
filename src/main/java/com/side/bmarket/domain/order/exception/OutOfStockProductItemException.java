package com.side.bmarket.domain.order.exception;

public class OutOfStockProductItemException extends RuntimeException {
    public OutOfStockProductItemException(String message) {
        super(message);
    }
}
