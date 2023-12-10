package com.side.bmarket.domain.cart.exception;

public class NotFoundCartItemException extends RuntimeException {
    public NotFoundCartItemException(String message) {
        super(message);
    }
}
