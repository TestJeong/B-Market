package com.side.bmarket.domain.category.exception;

public class NotFoundSubCategory extends RuntimeException {
    public NotFoundSubCategory(String message) {
        super(message);
    }
}
