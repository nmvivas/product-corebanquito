package com.banquito.core.product.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.banquito.core.product.dto.InterestRateDTO;
import com.banquito.core.product.model.InterestRate;

@Mapper
public interface InterestRateMapper {
    InterestRateMapper INSTANCE = Mappers.getMapper(InterestRateMapper.class);

    InterestRateDTO toInterestRateDTO(InterestRate interestRate);

    @Mapping(target = "code", source = "code")
    InterestRate toInterestRate(InterestRateDTO interestRateDTO);
}