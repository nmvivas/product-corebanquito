package com.banquito.core.product.dto;

import lombok.Builder;

@Builder
public class ProductTypeDTO {
    private String code;
    private String name;
    private String clientType;
    private String allowEarnInterest;
    private String temporalityInterest;

    public ProductTypeDTO(String code, String name, String clientType, String allowEarnInterest,
            String temporalityInterest) {
        this.code = code;
        this.name = name;
        this.clientType = clientType;
        this.allowEarnInterest = allowEarnInterest;
        this.temporalityInterest = temporalityInterest;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClientType() {
        return clientType;
    }
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
    public String getAllowEarnInterest() {
        return allowEarnInterest;
    }
    public void setAllowEarnInterest(String allowEarnInterest) {
        this.allowEarnInterest = allowEarnInterest;
    }
    public String getTemporalityInterest() {
        return temporalityInterest;
    }
    public void setTemporalityInterest(String temporalityInterest) {
        this.temporalityInterest = temporalityInterest;
    }
}
