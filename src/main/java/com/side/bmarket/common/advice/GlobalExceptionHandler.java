package com.side.bmarket.common.advice;

import com.side.bmarket.common.dto.ErrorDto;
import com.side.bmarket.common.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleServerException(Exception ex) {
        final ErrorDto errorDto = new ErrorDto(500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}
