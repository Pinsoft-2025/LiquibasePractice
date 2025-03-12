package com.staj.liquibasepractice.service;

import com.staj.liquibasepractice.entity.Category;
import com.staj.liquibasepractice.repository.CategoryRepository;
import com.staj.liquibasepractice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Ürünleri listeleyen,kategorileri listeleyen ve ürün detayını gösteren apiler
@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //kategorileri listeleyen
    public List<Category> findAll(){return categoryRepository.findAll();}

    public Category addCategory(Category category){return categoryRepository.save(category);}

    //delete katagory eklersem ve cidden silersem o kategorideki produclara ne olacak
}
