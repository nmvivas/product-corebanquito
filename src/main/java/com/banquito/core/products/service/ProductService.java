package com.banquito.core.products.service;

import com.banquito.core.products.model.Product;
import com.banquito.core.products.model.ProductPK;
import com.banquito.core.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return this.repository.findAll();
    }

    public Product getProductById(ProductPK pk) {
        Optional<Product> productOpt = this.repository.findById(pk);
        if (productOpt.isPresent()) {
            return productOpt.get();
        } else {
            throw new RuntimeException("No existe el producto con id: " + pk);
        }
    }

    public Product createProduct(Product product) {
        return this.repository.save(product);
    }

    public Product updateProduct(ProductPK pk, Product product) {
        if (this.repository.existsById(pk)) {
            return this.repository.save(product);
        } else {
            throw new RuntimeException("No existe el producto con id: " + pk);
        }
    }

    public void deleteProduct(ProductPK pk) {
        if (this.repository.existsById(pk)) {
            this.repository.deleteById(pk);
        } else {
            throw new RuntimeException("No existe el producto con id: " + pk);
        }
    }
}
