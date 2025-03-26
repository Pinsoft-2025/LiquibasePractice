package com.staj.liquibasepractice.service;

import com.staj.liquibasepractice.dto.request.CreateProductRequest;
import com.staj.liquibasepractice.dto.response.ProductDisplayResponse;
import com.staj.liquibasepractice.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDisplayResponse> findAll();
    ProductDisplayResponse findById(Long id);
    Product addProduct(CreateProductRequest request);
    Product updateProduct(CreateProductRequest request, Long id);
    void deleteProduct(Long id);
    List<String> simpleDisplay();
    List<ProductDisplayResponse> searchProduct(String search);
}
/*

2-
Logout, tokenin geçici bir yerde tutulması, ör:localstorage

 */