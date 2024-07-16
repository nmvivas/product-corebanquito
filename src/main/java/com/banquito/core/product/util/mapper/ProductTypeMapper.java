package com.banquito.core.product.util.mapper;

import com.banquito.core.product.dto.ProductTypeDTO;
import com.banquito.core.product.model.ProductType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductTypeMapper {
    ProductTypeMapper INSTANCE = Mappers.getMapper(ProductTypeMapper.class);

    ProductTypeDTO toProductTypeDTO(ProductType productType);

    ProductType toProductType(ProductTypeDTO productTypeDTO);
}
