package com.staj.liquibasepractice.entity.dto.request;

public record CreateUserRequest (
        String email,
        String username,
        String password
){}
