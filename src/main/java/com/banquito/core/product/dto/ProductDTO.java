package com.banquito.core.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDTO {
    private String codeInterestRate;
    private String codeSegment;
    private String name;
    private String state;
    private Date startDate;
    private Date endDate;
    private String allowDebitCard;
    private String allowTransferences;
    private BigDecimal minOpeningBalance;
    private String codeProductType;
}
