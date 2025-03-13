package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.entity.Product;
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
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){return ResponseEntity.ok(productService.updateProduct(product, id));}

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){productService.deleteProduct(id);}

    @GetMapping("/simple-display")
    public ResponseEntity<List<String>> simpleDisplay(){return new ResponseEntity<>(productService.simpleDisplay(), HttpStatus.OK);}
}
