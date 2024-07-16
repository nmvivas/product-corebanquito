package com.banquito.core.product.util.mapper;

import com.banquito.core.product.dto.InterestRateDTO;
import com.banquito.core.product.model.InterestRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterestRateMapper {
    InterestRateMapper INSTANCE = Mappers.getMapper(InterestRateMapper.class);

    InterestRateDTO toInterestRateDTO(InterestRate interestRate);

    InterestRate toInterestRate(InterestRateDTO interestRateDTO);
}
