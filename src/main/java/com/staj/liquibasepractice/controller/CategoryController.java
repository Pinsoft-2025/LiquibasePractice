package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.entity.Category;
import com.staj.liquibasepractice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Category>> findAll(){return ResponseEntity.ok(categoryService.findAll());}

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory( @RequestBody Category category){return  ResponseEntity.ok(categoryService.addCategory(category));}
}
