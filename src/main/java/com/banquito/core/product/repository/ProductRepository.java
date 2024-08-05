package com.banquito.core.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.product.model.Product;
import com.banquito.core.product.model.ProductPK;

public interface ProductRepository extends JpaRepository<Product, ProductPK> {
    Optional<Product> findByUniqueId(String uniqueId);
    void deleteByUniqueId(String uniqueId);
}
