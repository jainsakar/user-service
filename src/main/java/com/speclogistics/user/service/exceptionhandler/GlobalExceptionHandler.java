package com.speclogistics.user.service.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(WebExchangeBindException exception) {
        Map<String, String> resp = new HashMap<>();
        resp.put("status" , HttpStatus.BAD_REQUEST.toString());
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            resp.put("message",message);
        });
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException exception) {
        Map<String, String> resp = new HashMap<>();
        resp.put("status" , exception.getStatus().toString());
        resp.put("message" , exception.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
