package com.banquito.core.product.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.banquito.core.product.dto.ProductDTO;
import com.banquito.core.product.model.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toProductDTO(Product product);

    Product toProduct(ProductDTO productDTO);
}
