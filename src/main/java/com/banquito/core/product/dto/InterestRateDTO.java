package com.banquito.core.product.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InterestRateDTO {
    private String code;
    private String name;
    private String type;
    private BigDecimal daysInMonth;
    private BigDecimal daysInYear;
}