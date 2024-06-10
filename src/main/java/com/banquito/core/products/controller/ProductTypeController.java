package com.banquito.core.products.controller;

import com.banquito.core.products.model.ProductType;
import com.banquito.core.products.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product-types")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        return ResponseEntity.ok(this.productTypeService.getAllProductTypes());
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable String code) {
        try {
            return ResponseEntity.ok(this.productTypeService.getProductTypeById(code));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
        return ResponseEntity.ok(this.productTypeService.createProductType(productType));
    }

    @PutMapping("/{code}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable String code,
            @RequestBody ProductType productType) {
        return ResponseEntity.ok(this.productTypeService.updateProductType(code, productType));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProductType(@PathVariable String code) {
        this.productTypeService.deleteProductType(code);
        return ResponseEntity.noContent().build();
    }
}
