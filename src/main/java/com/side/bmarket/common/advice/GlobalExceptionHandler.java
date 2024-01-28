package com.side.bmarket.common.advice;

import com.side.bmarket.common.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorDto> handleServerException(Exception ex) {
        final ErrorDto errorDto = new ErrorDto(500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    ResponseEntity<ErrorDto> handleRequestParameterException(MissingServletRequestParameterException ex) {
        final ErrorDto errorDto = new ErrorDto(400, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> exampleResponseValidation(MethodArgumentNotValidException ex) {

        Map<String, String> error = new HashMap<>();
        ex.getAllErrors().forEach(
                c -> error.put(((FieldError) c).getField(), c.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
