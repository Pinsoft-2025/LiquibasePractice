package com.staj.liquibasepractice.dto.response;

public record LogResponse(
        String email,
        String username,
        String Role,
        String token
) {}
