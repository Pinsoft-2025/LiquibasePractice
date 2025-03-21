package com.staj.liquibasepractice.service;

import com.staj.liquibasepractice.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
