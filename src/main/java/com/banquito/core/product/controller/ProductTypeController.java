package com.banquito.core.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.product.model.ProductType;
import com.banquito.core.product.service.ProductTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/product-microservice/api/v1/product-type")
@Tag(name = "ProductType Management System", description = "Operations pertaining to product types in ProductType Management System")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Operation(summary = "View a list of available product types", description = "Get a list of all product types")
    @GetMapping
    public List<ProductType> getAllProductTypes() {
        return productTypeService.getAllProductTypes();
    }

    @Operation(summary = "Get a product type by uniqueId", description = "Get a single product type by its uniqueId")
    @GetMapping("/{uniqueId}")
    public ProductType getProductTypeByUniqueId(@PathVariable("uniqueId") String uniqueId) {
        return productTypeService.getProductTypeByUniqueId(uniqueId);
    }

    @Operation(summary = "Add a product type", description = "Create a new product type")
    @PostMapping
    public ProductType createProductType(@RequestBody ProductType productType) {
        return productTypeService.saveProductType(productType);
    }

    @Operation(summary = "Update a product type", description = "Update an existing product type")
    @PutMapping("/{uniqueId}")
    public ProductType updateProductType(@PathVariable("uniqueId") String uniqueId, @RequestBody ProductType productType) {
        productType.setUniqueId(uniqueId);
        return productTypeService.saveProductType(productType);
    }

    @Operation(summary = "Delete a product type", description = "Delete an existing product type by its uniqueId")
    @DeleteMapping("/{uniqueId}")
    public void deleteProductType(@PathVariable("uniqueId") String uniqueId) {
        productTypeService.deleteProductType(uniqueId);
    }
}
