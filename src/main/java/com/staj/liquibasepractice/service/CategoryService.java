package com.staj.liquibasepractice.service;

import com.staj.liquibasepractice.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category addCategory(Category category);
}
