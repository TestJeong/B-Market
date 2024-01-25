package com.side.bmarket.common.dto;

import lombok.Getter;

@Getter
public class ErrorDto {
    private final int code;
    private final String message;

    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
