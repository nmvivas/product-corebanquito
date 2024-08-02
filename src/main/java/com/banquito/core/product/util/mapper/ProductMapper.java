package com.banquito.core.product.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.banquito.core.product.dto.ProductDTO;
import com.banquito.core.product.model.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "pk.code", target = "code")
    @Mapping(source = "pk.codeProductType", target = "codeProductType")
    @Mapping(source = "interestRate.code", target = "codeInterestRate")
    ProductDTO toProductDTO(Product product);

    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "interestRate", ignore = true)
    Product toProduct(ProductDTO productDTO);
}
