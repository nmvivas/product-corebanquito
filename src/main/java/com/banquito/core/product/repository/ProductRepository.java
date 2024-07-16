package com.banquito.core.product.repository;

import com.banquito.core.product.model.Product;
import com.banquito.core.product.model.ProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductPK> {
}
