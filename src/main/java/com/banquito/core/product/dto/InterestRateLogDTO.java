package com.banquito.core.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InterestRateLogDTO {
    private Integer id;
    private String codeInterestRate;
    private BigDecimal value;
    private Date startDate;
    private Date endDate;
    private String state;
}
