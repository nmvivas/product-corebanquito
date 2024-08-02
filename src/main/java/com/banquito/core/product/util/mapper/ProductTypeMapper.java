package com.banquito.core.product.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.banquito.core.product.dto.ProductTypeDTO;
import com.banquito.core.product.model.ProductType;

@Mapper
public interface ProductTypeMapper {
    ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);

    ProductTypeDTO toProductTypeDTO(ProductType productType);

    @Mapping(target = "code", source = "code")
    ProductType toProductType(ProductTypeDTO productTypeDTO);
}

