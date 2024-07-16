package com.banquito.core.product.service;

import com.banquito.core.product.model.Product;
import com.banquito.core.product.model.ProductPK;
import com.banquito.core.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(ProductPK pk) {
        return productRepository.findById(pk).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(ProductPK pk) {
        productRepository.deleteById(pk);
    }
}
