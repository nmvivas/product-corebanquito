package com.banquito.core.product.util.mapper;

import com.banquito.core.product.dto.InterestRateLogDTO;
import com.banquito.core.product.model.InterestRateLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterestRateLogMapper {
    InterestRateLogMapper INSTANCE = Mappers.getMapper(InterestRateLogMapper.class);

    InterestRateLogDTO toInterestRateLogDTO(InterestRateLog interestRateLog);

    InterestRateLog toInterestRateLog(InterestRateLogDTO interestRateLogDTO);
}
