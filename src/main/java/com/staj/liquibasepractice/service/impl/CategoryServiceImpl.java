package com.staj.liquibasepractice.service.impl;

import com.staj.liquibasepractice.entity.Category;
import com.staj.liquibasepractice.repository.CategoryRepository;
import com.staj.liquibasepractice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

// Ürünleri listeleyen,kategorileri listeleyen ve ürün detayını gösteren apiler
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //kategorileri listeleyen
    @Override
    public List<Category> findAll(){return categoryRepository.findAll();}

    @Override
    public Category addCategory(Category category){return categoryRepository.save(category);}

    //delete katagory eklersem ve cidden silersem o kategorideki produclara ne olacak
}
