package com.staj.liquibasepractice.dto.response;

public record OrderResponce(
        String name,
        float price,
        int quantity
) {
}
