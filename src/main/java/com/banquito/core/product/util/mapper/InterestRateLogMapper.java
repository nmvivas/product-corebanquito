package com.banquito.core.product.util.mapper;

import com.banquito.core.product.dto.InterestRateLogDTO;
import com.banquito.core.product.model.InterestRateLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterestRateLogMapper {
    InterestRateLogMapper INSTANCE = Mappers.getMapper(InterestRateLogMapper.class);

    @Mapping(source = "interestRate.code", target = "codeInterestRate")
    InterestRateLogDTO toInterestRateLogDTO(InterestRateLog interestRateLog);

    @Mapping(target = "interestRate", ignore = true) // Add appropriate mapping if necessary
    InterestRateLog toInterestRateLog(InterestRateLogDTO interestRateLogDTO);
}
