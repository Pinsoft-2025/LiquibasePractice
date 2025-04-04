package com.staj.liquibasepractice.dto.request;

import com.staj.liquibasepractice.entity.Category;
import org.springframework.web.multipart.MultipartFile;

public record CreateProductRequest(
        String name,
        float price,
        String explanation,
        Category category,
        MultipartFile imageFile
) {
}
