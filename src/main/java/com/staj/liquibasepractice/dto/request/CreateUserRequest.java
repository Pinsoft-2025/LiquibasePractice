package com.staj.liquibasepractice.dto.request;

public record CreateUserRequest (
        String email,
        String username,
        String password
){}
