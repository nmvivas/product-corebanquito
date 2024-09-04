package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

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

    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#uniqueId")
    public Product getProductByUniqueId(String uniqueId) {
        return productRepository.findByUniqueId(uniqueId).orElse(null);
    }

    @CachePut(value = "products", key = "#product.uniqueId")
    public Product saveProduct(Product product) {
        if (product.getUniqueId() == null || product.getUniqueId().isEmpty()) {
            product.setUniqueId(uniqueIdGeneration.generateUniqueId());
        }
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", key = "#uniqueId")
    public void deleteProduct(String uniqueId) {
        productRepository.deleteByUniqueId(uniqueId);
    }
}
