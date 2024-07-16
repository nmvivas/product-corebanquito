package com.banquito.core.product.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductTypeDTO {
    private String code;
    private String name;
    private String clientType;
    private String allowEarnInterest;
    private String temporalityInterest;
}
