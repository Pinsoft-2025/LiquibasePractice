package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.dto.request.CreateProductRequest;
import com.staj.liquibasepractice.dto.response.ProductDisplayResponse;
import com.staj.liquibasepractice.entity.Product;
import com.staj.liquibasepractice.exceptions.ProductNotFoundException;
import com.staj.liquibasepractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    //baya berbat çalışıyo, döngüye giriyo. nasıl tüm productları göndercez? formatlamak lazım
    //belki dto
    @GetMapping("/user/find-all")
    public ResponseEntity<List<ProductDisplayResponse>> findAll(){
        List<ProductDisplayResponse> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/user/find-by-id/{id}")
    public ResponseEntity<ProductDisplayResponse> findById(@PathVariable Long id){
            return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<Product> addProduct(@RequestBody CreateProductRequest request){
        return new ResponseEntity<>(productService.addProduct(request), HttpStatus.CREATED);
    }

    @PostMapping("/admin/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody CreateProductRequest request, @PathVariable Long id){return ResponseEntity.ok(productService.updateProduct(request, id));}

    @DeleteMapping("/admin/delete/{id}")
    public void deleteProduct(@PathVariable Long id){productService.deleteProduct(id);}

    @GetMapping("/user/simple-display")
    public ResponseEntity<List<String>> simpleDisplay(){return new ResponseEntity<>(productService.simpleDisplay(), HttpStatus.OK);}

    @GetMapping("/user/search/{promt}")
    public ResponseEntity<List<ProductDisplayResponse>> searchProduct(@PathVariable String promt){
        return new ResponseEntity<>(productService.searchProduct(promt),HttpStatus.FOUND);
    }
}

