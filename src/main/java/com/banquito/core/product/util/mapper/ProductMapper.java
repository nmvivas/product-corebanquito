package com.banquito.core.product.util.mapper;

import com.banquito.core.product.dto.ProductDTO;
import com.banquito.core.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "pk.codeProductType", target = "codeProductType")
    @Mapping(source = "productType.code", target = "codeProductType")
    @Mapping(source = "interestRate.code", target = "codeInterestRate")
    ProductDTO toProductDTO(Product product);

    @Mapping(target = "pk", ignore = true) // Add appropriate mapping if necessary
    @Mapping(target = "productType", ignore = true) // Add appropriate mapping if necessary
    @Mapping(target = "interestRate", ignore = true) // Add appropriate mapping if necessary
    Product toProduct(ProductDTO productDTO);
}
