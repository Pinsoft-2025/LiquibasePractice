package com.staj.liquibasepractice.dto.request;

public record RegisterRequest(
        String email,
        String username,
        String password) {
}
