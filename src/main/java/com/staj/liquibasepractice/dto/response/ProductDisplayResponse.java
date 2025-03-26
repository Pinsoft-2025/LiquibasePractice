package com.staj.liquibasepractice.dto.response;

import org.springframework.web.multipart.MultipartFile;

public record ProductDisplayResponse(
        String name,
        float price,
        String explanation,
        String categoryName,
        String imageFile
) {}
