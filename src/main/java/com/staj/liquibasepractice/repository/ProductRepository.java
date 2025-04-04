package com.staj.liquibasepractice.repository;

import com.staj.liquibasepractice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String search);

    List<Product> findByCategory_Name(String category);
}
