package com.staj.liquibasepractice.service;

import com.staj.liquibasepractice.entity.User;
import com.staj.liquibasepractice.dto.request.CreateUserRequest;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    User createUser(CreateUserRequest request);
}
