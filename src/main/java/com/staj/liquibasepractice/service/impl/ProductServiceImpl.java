package com.staj.liquibasepractice.service.impl;

import com.staj.liquibasepractice.dto.request.CreateProductRequest;
import com.staj.liquibasepractice.dto.response.ProductDisplayResponse;
import com.staj.liquibasepractice.entity.Product;
import com.staj.liquibasepractice.exceptions.ProductNotFoundException;
import com.staj.liquibasepractice.repository.ProductRepository;
import com.staj.liquibasepractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

// Ürünleri listeleyen,kategorileri listeleyen ve ürün detayını gösteren apiler
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDisplayResponse> findAll(){
        List<Product> products = productRepository.findAll();
        validateProductList(products);
       return products.stream().map(this::productToResponse).toList();
    }

    @Override
    public ProductDisplayResponse findById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return productToResponse(product);
    }

    @Override
    public Product addProduct(CreateProductRequest request){
        validateFile(request);
        Product product = new Product();
        product = updateProductFields(product, request);
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product updateProduct(CreateProductRequest request, Long id){
        Product old = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        validateFile(request);
        old = updateProductFields(old, request);
        return productRepository.save(old);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id){
        Product toDelete = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(toDelete);
    }

    @Override
    public List<String> simpleDisplay(){
        List<Product> products =  productRepository.findAll();
        validateProductList(products);

        return products
                .stream()
                .map(product -> "Product " + product.getId() + "| " + product.getName() + "| " + product.getPrice() + "TL" )
                .toList();
    }

    @Override
    public List<ProductDisplayResponse> searchProduct(String search){
        List<Product> products = productRepository.findByNameContaining(search);
        return validateProductList(products).stream()
                .map(this::productToResponse).toList();
    }

    @Override
    public List<ProductDisplayResponse> searchProductByCategory(String category) {
        List<Product> products = productRepository.findByCategory_Name(category);
        return validateProductList(products).stream()
                .map(this::productToResponse).toList();
    }


    //<<<<<<<<<<<<< PRIVTE METHODS >>>>>>>>>>>>>>

    //checks if there are any null products in a list
    private List<Product> validateProductList(List<Product> products){
        if (products.isEmpty()) {
            throw new ProductNotFoundException(); //custom exception
        }else {
            return products;
        }
    }

    //IMAGE METHOD
    //checks if the file is usable as image
    private void validateFile(CreateProductRequest request){
        String fileName = StringUtils.cleanPath(request.imageFile().getOriginalFilename());

        if(fileName.contains(".."))
        {
            throw new ProductNotFoundException("not a a valid file, can't find image"); //would be better if I TODO make custom exception
        }
    }

    // Product -> ProductDisplayResponse(DTO)
    private ProductDisplayResponse productToResponse(Product product){
        return new ProductDisplayResponse(
                product.getName(),
                product.getPrice(),
                product.getExplanation(),
                product.getCategory().getName(),
                product.getBase64Image()
        );
    }

    //IMAGE METHOD
    //transforms Image in Base64 to store in DB
    private String encodeImageToBase64(MultipartFile imageFile) {
        try {
            return Base64.getEncoder().encodeToString(imageFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error encoding image to Base64", e);//would be better if I TODO make custom exception
        }
    }

    //Updates existing product
    private Product updateProductFields(Product product, CreateProductRequest request) {
        product.setName(request.name());
        product.setPrice(request.price());
        product.setExplanation(request.explanation());
        product.setCategory(request.category());
        product.setBase64Image(encodeImageToBase64(request.imageFile()));
        return product;
    }

}
