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

import java.util.List;
import java.util.stream.Collectors;

// Ürünleri listeleyen,kategorileri listeleyen ve ürün detayını gösteren apiler
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDisplayResponse> findAll(){
       return validateProductList().stream().map(product ->
                new ProductDisplayResponse(
                        product.getName(),
                        product.getPrice(),
                        product.getExplanation(),
                        product.getCategory().getName()
                )
       ).toList();
    }

    @Override
    public ProductDisplayResponse findById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return new ProductDisplayResponse(
                product.getName(),
                product.getPrice(),
                product.getExplanation(),
                product.getCategory().getName()
        );
    }

    @Override
    public Product addProduct(CreateProductRequest request){
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .explanation(request.explanation())
                .category(request.category())
                .build();
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product updateProduct(CreateProductRequest request, Long id){
        Product old = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        old.setName(request.name());
        old.setPrice(request.price());
        old.setExplanation(request.explanation());
        old.setCategory(request.category());

        return productRepository.save(old);
    }

    @Transactional
    @Override
    public void deleteProduct(Long id){
        Product toDelete = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));;
        productRepository.delete(toDelete);
    }

    @Override
    public List<String> simpleDisplay(){
        List<Product> products = validateProductList();

        return products
                .stream()
                .map(product -> "Product " + product.getId() + "| " + product.getName() + "| " + product.getPrice() + "TL" )
                .collect(Collectors.toList());
    }

    private List<Product> validateProductList(){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        }else {
            return products;
        }
    }

}
