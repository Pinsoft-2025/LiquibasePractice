package com.staj.liquibasepractice.dto.request;

public record OrderRequest(
    String name,
    float price,
    int quantity,
    Long userId
) {
}
