package com.banquito.core.products.service;

import com.banquito.core.products.model.ProductType;
import com.banquito.core.products.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    private final ProductTypeRepository repository;

    public ProductTypeService(ProductTypeRepository repository) {
        this.repository = repository;
    }

    public List<ProductType> getAllProductTypes() {
        return this.repository.findAll();
    }

    public ProductType getProductTypeById(String code) {
        Optional<ProductType> productTypeOpt = this.repository.findById(code);
        if (productTypeOpt.isPresent()) {
            return productTypeOpt.get();
        } else {
            throw new RuntimeException("No existe el tipo de producto con código: " + code);
        }
    }

    public ProductType createProductType(ProductType productType) {
        return this.repository.save(productType);
    }

    public ProductType updateProductType(String code, ProductType productType) {
        if (this.repository.existsById(code)) {
            return this.repository.save(productType);
        } else {
            throw new RuntimeException("No existe el tipo de producto con código: " + code);
        }
    }

    public void deleteProductType(String code) {
        if (this.repository.existsById(code)) {
            this.repository.deleteById(code);
        } else {
            throw new RuntimeException("No existe el tipo de producto con código: " + code);
        }
    }
}
