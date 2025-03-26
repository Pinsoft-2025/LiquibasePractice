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
}
/*
1-
"Like" sql query ile yazılana benzer ürünler döndürmek,
bunu seçilen kategorilere sınırlandırarak göstermek

2-
Logout, tokenin geçici bir yerde tutulması, ör:localstorage

3-
 Product tablosuna “base64Image” kolonu ekleyin.
 Product tablosuna veri ekleyen api’yi yazın. Figmaya baktığınızda ürünlerin bir
 görselinin de eklenmesi gerektiğini istediğimi anlayacaksınız. Bunu göz önüne alarak
 bu apiyi yazın
 */