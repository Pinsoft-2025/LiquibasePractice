package com.staj.liquibasepractice.dto.response;


import java.time.LocalDateTime;

public record ErrorResponse(
    String message,
    String details,
    String path,
    LocalDateTime timestamp
){}
