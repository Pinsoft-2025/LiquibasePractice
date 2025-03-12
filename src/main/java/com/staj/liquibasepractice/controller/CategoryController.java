package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.entity.Category;
import com.staj.liquibasepractice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Category>> findAll(){return ResponseEntity.ok(categoryService.findAll());}

    public ResponseEntity<Category> addCategory( Category category){return  ResponseEntity.ok(categoryService.addCategory(category));}
}
