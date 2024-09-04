package com.banquito.core.product.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
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

    @Cacheable("productTypes")
    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    @Cacheable(value = "productTypes", key = "#uniqueId")
    public ProductType getProductTypeByUniqueId(String uniqueId) {
        return productTypeRepository.findByUniqueId(uniqueId).orElse(null);
    }

    @CachePut(value = "productTypes", key = "#productType.uniqueId")
    public ProductType saveProductType(ProductType productType) {
        if (productType.getUniqueId() == null || productType.getUniqueId().isEmpty()) {
            productType.setUniqueId(uniqueIdGeneration.generateUniqueId());
        }
        return productTypeRepository.save(productType);
    }

    @CacheEvict(value = "productTypes", key = "#uniqueId")
    public void deleteProductType(String uniqueId) {
        productTypeRepository.deleteByUniqueId(uniqueId);
    }
}
