package com.banquito.core.products.repository;

import com.banquito.core.products.model.ProductType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, String> {

}
