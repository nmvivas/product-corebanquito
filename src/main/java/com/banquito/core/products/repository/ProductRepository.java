package com.banquito.core.products.repository;

import com.banquito.core.products.model.Product;
import com.banquito.core.products.model.ProductPK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductPK> {

}
