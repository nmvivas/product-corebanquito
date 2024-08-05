package com.banquito.core.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.product.model.ProductType;
import com.banquito.core.product.repository.ProductTypeRepository;
import com.banquito.core.product.util.UniqueIdGeneration;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final UniqueIdGeneration uniqueIdGeneration;

    public ProductTypeService(ProductTypeRepository productTypeRepository, UniqueIdGeneration uniqueIdGeneration) {
        this.productTypeRepository = productTypeRepository;
        this.uniqueIdGeneration = uniqueIdGeneration;
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public ProductType getProductTypeByUniqueId(String uniqueId) {
        return productTypeRepository.findByUniqueId(uniqueId).orElse(null);
    }

    public ProductType saveProductType(ProductType productType) {
        if (productType.getUniqueId() == null || productType.getUniqueId().isEmpty()) {
            productType.setUniqueId(uniqueIdGeneration.generateUniqueId());
        }
        return productTypeRepository.save(productType);
    }

    public void deleteProductType(String uniqueId) {
        productTypeRepository.deleteByUniqueId(uniqueId);
    }
}
