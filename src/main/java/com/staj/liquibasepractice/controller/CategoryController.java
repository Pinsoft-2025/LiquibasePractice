package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.entity.Category;
import com.staj.liquibasepractice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/user/find-all")
    public ResponseEntity<List<Category>> findAll(){return ResponseEntity.ok(categoryService.findAll());}

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){return  ResponseEntity.ok(categoryService.addCategory(category));}
}
