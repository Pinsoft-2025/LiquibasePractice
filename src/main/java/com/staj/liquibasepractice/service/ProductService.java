package com.staj.liquibasepractice.service;

import com.staj.liquibasepractice.entity.Product;
import com.staj.liquibasepractice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Ürünleri listeleyen,kategorileri listeleyen ve ürün detayını gösteren apiler
@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){return productRepository.findAll();}

    public Product findById(Long id){return productRepository.findById(id).get();}

    public Product addProduct(Product product){return productRepository.save(product);}

    public Product updateProduct(Product product, Long id){
        Product old = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        old.setName(product.getName());
        old.setPrice(product.getPrice());
        old.setExplanation(product.getExplanation());

        return productRepository.save(old);
    }

    public void deleteProduct(Long id){
        Product toDelete = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(toDelete);
    }

    public List<String> simpleDisplay(){
        List<Product> products = productRepository.findAll();

        return products
                .stream()
                .map(product -> "Product " + product.getId() + "| " + product.getName() + "| " + product.getPrice() + "TL" )
                .collect(Collectors.toList());
    }
}
