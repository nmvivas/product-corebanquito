package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.product.model.Product;
import com.banquito.core.product.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByUniqueId(String uniqueId) {
        return productRepository.findByUniqueId(uniqueId).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String uniqueId) {
        productRepository.deleteByUniqueId(uniqueId);
    }
}
