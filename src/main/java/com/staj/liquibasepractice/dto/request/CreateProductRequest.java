package com.staj.liquibasepractice.dto.request;

import com.staj.liquibasepractice.entity.Category;

public record CreateProductRequest(
        String name,
        float price,
        String explanation,
        Category category) {
}
