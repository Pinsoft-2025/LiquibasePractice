package com.staj.liquibasepractice.controller;

import com.staj.liquibasepractice.dto.request.CreateProductRequest;
import com.staj.liquibasepractice.dto.response.ProductDisplayResponse;
import com.staj.liquibasepractice.entity.Category;
import com.staj.liquibasepractice.entity.Product;
import com.staj.liquibasepractice.service.CategoryService;
import com.staj.liquibasepractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

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

    @PostMapping(path = "/admin/add", consumes = "multipart/form-data")
    public ResponseEntity<Product> addProduct(@RequestParam("name") String name,
                                              @RequestParam("price") float price,
                                              @RequestParam("explanation") String explanation,
                                              @RequestParam("category") String categoryName,
                                              @RequestParam("imageFile") MultipartFile imageFile)
    {
        Category category = categoryService.findCategoryByName(categoryName);
        CreateProductRequest request = new CreateProductRequest(name, price, explanation, category, imageFile);
        return new ResponseEntity<>(productService.addProduct(request), HttpStatus.CREATED);
    }


    @PostMapping(path = "/admin/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Product> updateProduct(@RequestParam("name") String name,
                                                 @RequestParam("price") float price,
                                                 @RequestParam("explanation") String explanation,
                                                 @RequestParam("category") String categoryName,
                                                 @RequestParam("imageFile") MultipartFile imageFile,
                                                 @PathVariable Long id)
    {
        Category category = categoryService.findCategoryByName(categoryName);

        CreateProductRequest request = new CreateProductRequest(name, price, explanation, category, imageFile);
        return ResponseEntity.ok(productService.updateProduct(request, id));
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteProduct(@PathVariable Long id){productService.deleteProduct(id);}

    @GetMapping("/user/simple-display")
    public ResponseEntity<List<String>> simpleDisplay(){return new ResponseEntity<>(productService.simpleDisplay(), HttpStatus.OK);}

    @GetMapping("/user/search/{prompt}")
    public ResponseEntity<List<ProductDisplayResponse>> searchProduct(@PathVariable String prompt){
        return new ResponseEntity<>(productService.searchProduct(prompt),HttpStatus.FOUND);
    }

    @GetMapping("/user/search-by-category/{category}")
    public ResponseEntity<List<ProductDisplayResponse>> searchProductByCategory(@PathVariable String category){
        return new ResponseEntity<>(productService.searchProductByCategory(category),HttpStatus.FOUND);
    }
}

