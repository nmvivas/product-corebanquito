package com.banquito.core.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.product.model.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, String> {
    Optional<ProductType> findByUniqueId(String uniqueId);
    void deleteByUniqueId(String uniqueId);
}
