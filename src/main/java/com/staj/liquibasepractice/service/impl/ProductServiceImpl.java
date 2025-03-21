package com.staj.liquibasepractice.service.impl;

import com.staj.liquibasepractice.entity.Product;
import com.staj.liquibasepractice.exceptions.ProductNotFoundException;
import com.staj.liquibasepractice.repository.ProductRepository;
import com.staj.liquibasepractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Product> findAll(){return validateProductList();}

    @Override
    public Product findById(Long id){return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));}

    @Override
    public Product addProduct(Product product){return productRepository.save(product);}

    @Transactional
    @Override
    public Product updateProduct(Product product, Long id){
        Product old = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        old.setName(product.getName());
        old.setPrice(product.getPrice());
        old.setExplanation(product.getExplanation());

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
