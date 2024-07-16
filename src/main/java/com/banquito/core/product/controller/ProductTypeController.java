package com.banquito.core.product.controller;

import com.banquito.core.product.dto.ProductTypeDTO;
import com.banquito.core.product.service.ProductTypeService;
import com.banquito.core.product.util.mapper.ProductTypeMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product-types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping
    public List<ProductTypeDTO> getAllProductTypes() {
        return productTypeService.getAllProductTypes().stream()
                .map(ProductTypeMapper.INSTANCE::toProductTypeDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductTypeDTO getProductTypeById(@PathVariable("id") String code) {
        return ProductTypeMapper.INSTANCE.toProductTypeDTO(productTypeService.getProductTypeById(code));
    }

    @PostMapping
    public ProductTypeDTO createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return ProductTypeMapper.INSTANCE.toProductTypeDTO(
                productTypeService.saveProductType(ProductTypeMapper.INSTANCE.toProductType(productTypeDTO)));
    }

    @PutMapping("/{id}")
    public ProductTypeDTO updateProductType(@PathVariable("id") String code,
            @RequestBody ProductTypeDTO productTypeDTO) {
        // Since DTOs are immutable, recreate the DTO with the code
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

    @DeleteMapping("/{id}")
    public void deleteProductType(@PathVariable("id") String code) {
        productTypeService.deleteProductType(code);
    }
}
