package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.entity.Product;
import com.staj.liquibasepractice.exceptions.ProductNotFoundException;
import com.staj.liquibasepractice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //baya berbat çalışıyo, döngüye giriyo. nasıl tüm productları göndercez? formatlamak lazım
    //belki dto
    @GetMapping("/find-all")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        }else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(findProductById(id)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){return ResponseEntity.ok(productService.updateProduct(product, findProductById(id)));}

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){productService.deleteProduct(findProductById(id));}

    @GetMapping("/simple-display")
    public ResponseEntity<List<String>> simpleDisplay(){return new ResponseEntity<>(productService.simpleDisplay(), HttpStatus.OK);}

    private Long findProductById(Long id){
        Product product = productService.findById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }else {
            return id;
        }
    }
}

