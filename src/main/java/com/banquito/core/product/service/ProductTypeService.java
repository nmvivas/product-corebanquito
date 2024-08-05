package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.product.model.ProductType;
import com.banquito.core.product.repository.ProductTypeRepository;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public ProductType getProductTypeByUniqueId(String uniqueId) {
        return productTypeRepository.findByUniqueId(uniqueId).orElse(null);
    }

    public ProductType saveProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public void deleteProductType(String uniqueId) {
        productTypeRepository.deleteByUniqueId(uniqueId);
    }
}
