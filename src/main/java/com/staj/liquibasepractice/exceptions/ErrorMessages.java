package com.staj.liquibasepractice.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found");

    private final String message;

    private ErrorMessages(String message) {
        this.message = message;
    }

}
