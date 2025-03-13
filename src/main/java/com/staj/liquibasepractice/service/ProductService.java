package com.staj.liquibasepractice.service;

import com.staj.liquibasepractice.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product addProduct(Product product);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
    List<String> simpleDisplay();
}
