package com.staj.liquibasepractice.dto.response;

public record ProductDisplayResponse(
        String name,
        float price,
        String explanation,
        String categoryName
) {}
