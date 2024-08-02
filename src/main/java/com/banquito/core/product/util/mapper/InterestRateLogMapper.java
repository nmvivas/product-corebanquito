package com.banquito.core.product.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.banquito.core.product.dto.InterestRateLogDTO;
import com.banquito.core.product.model.InterestRateLog;

@Mapper
public interface InterestRateLogMapper {
    InterestRateLogMapper INSTANCE = Mappers.getMapper(InterestRateLogMapper.class);

    @Mapping(source = "codeInterestRate", target = "interestRate.code")
    InterestRateLogDTO toInterestRateLogDTO(InterestRateLog interestRateLog);

    @Mapping(target = "interestRate", ignore = true)
    InterestRateLog toInterestRateLog(InterestRateLogDTO interestRateLogDTO);
}
