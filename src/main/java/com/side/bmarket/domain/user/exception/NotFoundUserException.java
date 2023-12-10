package com.side.bmarket.domain.user.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(final String message) {
        super(message);
    }
}

