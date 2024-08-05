package com.banquito.core.product.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.banquito.core.product.dto.ProductTypeDTO;
import com.banquito.core.product.service.ProductTypeService;
import com.banquito.core.product.util.mapper.ProductTypeMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
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
    public List<ProductTypeDTO> getAllProductTypes() {
        return productTypeService.getAllProductTypes().stream()
                .map(ProductTypeMapper.INSTANCE::toProductTypeDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a product type by Id", description = "Get a single product type by its ID")
    @GetMapping("/{id}")
    public ProductTypeDTO getProductTypeById(@PathVariable("id") String code) {
        return ProductTypeMapper.INSTANCE.toProductTypeDTO(productTypeService.getProductTypeById(code));
    }

    @Operation(summary = "Add a product type", description = "Create a new product type")
    @PostMapping
    public ProductTypeDTO createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return ProductTypeMapper.INSTANCE.toProductTypeDTO(
                productTypeService.saveProductType(ProductTypeMapper.INSTANCE.toProductType(productTypeDTO)));
    }

    @Operation(summary = "Update a product type", description = "Update an existing product type")
    @PutMapping("/{id}")
    public ProductTypeDTO updateProductType(@PathVariable("id") String code,
            @RequestBody ProductTypeDTO productTypeDTO) {
        ProductTypeDTO updatedDTO = ProductTypeDTO.builder()
                .code(code)
                .name(productTypeDTO.getName())
                .clientType(productTypeDTO.getClientType())
                .allowEarnInterest(productTypeDTO.getAllowEarnInterest())
                .temporalityInterest(productTypeDTO.getTemporalityInterest())
                .build();

        return ProductTypeMapper.INSTANCE.toProductTypeDTO(
                productTypeService.saveProductType(ProductTypeMapper.INSTANCE.toProductType(updatedDTO)));
    }

    @Operation(summary = "Delete a product type", description = "Delete an existing product type by its ID")
    @DeleteMapping("/{id}")
    public void deleteProductType(@PathVariable("id") String code) {
        productTypeService.deleteProductType(code);
    }
}
