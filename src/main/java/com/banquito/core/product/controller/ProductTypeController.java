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

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT })
@RestController
@RequestMapping("/api/v1/product-type")
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
