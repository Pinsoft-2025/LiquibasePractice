package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.dto.response.ErrorResponse;
import com.staj.liquibasepractice.exceptions.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException e, HttpServletRequest request) {
        return new ErrorResponse(
                e.getMessage(),
                e.getDetails(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
    }
}
