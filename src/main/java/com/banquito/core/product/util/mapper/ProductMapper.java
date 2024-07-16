package com.banquito.core.product.util.mapper;

import com.banquito.core.product.dto.ProductDTO;
import com.banquito.core.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "pk.code", target = "code")
    @Mapping(source = "pk.codeProductType", target = "codeProductType")
    @Mapping(source = "interestRate.code", target = "codeInterestRate")
    ProductDTO toProductDTO(Product product);

    @Mapping(target = "pk", ignore = true) // or specify the mapping if necessary
    @Mapping(target = "productType", ignore = true) // Handle this mapping separately if needed
    @Mapping(target = "interestRate", ignore = true) // Handle this mapping separately if needed
    Product toProduct(ProductDTO productDTO);
}
