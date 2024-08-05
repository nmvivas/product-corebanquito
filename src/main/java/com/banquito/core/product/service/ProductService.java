package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.product.model.Product;
import com.banquito.core.product.repository.ProductRepository;
import com.banquito.core.product.util.UniqueIdGeneration;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UniqueIdGeneration uniqueIdGeneration;

    public ProductService(ProductRepository productRepository, UniqueIdGeneration uniqueIdGeneration) {
        this.productRepository = productRepository;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByUniqueId(String uniqueId) {
        return productRepository.findByUniqueId(uniqueId).orElse(null);
    }

    public Product saveProduct(Product product) {
        if (product.getUniqueId() == null || product.getUniqueId().isEmpty()) {
            product.setUniqueId(uniqueIdGeneration.generateUniqueId());
        }
        return productRepository.save(product);
    }

    public void deleteProduct(String uniqueId) {
        productRepository.deleteByUniqueId(uniqueId);
    }
}
