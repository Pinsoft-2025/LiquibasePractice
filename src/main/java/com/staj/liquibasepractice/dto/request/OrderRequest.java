package com.staj.liquibasepractice.dto.request;

import com.staj.liquibasepractice.entity.Product;

import java.util.List;

public record OrderRequest(
    String name,
    float price,
    int quantity,
    Long userId,
    List<Product> products
) {
}
