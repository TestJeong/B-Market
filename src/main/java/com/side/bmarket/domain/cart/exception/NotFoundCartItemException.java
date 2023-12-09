package com.side.bmarket.domain.cart.exception;

public class NotFoundCartItemException extends RuntimeException {
    public NotFoundCartItemException(final String message) {
        super(message);
    }
}
