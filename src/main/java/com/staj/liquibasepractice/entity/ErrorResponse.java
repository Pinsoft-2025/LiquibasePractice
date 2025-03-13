package com.staj.liquibasepractice.entity;


import java.time.LocalDateTime;

public record ErrorResponse(
    String message,
    String details,
    String path,
    LocalDateTime timestamp
){}
