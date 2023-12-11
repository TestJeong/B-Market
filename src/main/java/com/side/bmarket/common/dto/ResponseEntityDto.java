package com.side.bmarket.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseEntityDto<T> {
    private int code;
    private HttpStatus status;
    private String message;
    private T data;

    public ResponseEntityDto(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntityDto<T> of(HttpStatus httpStatus, T data) {
        return new ResponseEntityDto<>(httpStatus, httpStatus.name(), data);
    }

}
