package com.banquito.core.product.dto;

import java.math.BigDecimal;

import lombok.Builder;
@Builder
public class InterestRateDTO {
    private String code;
    private String name;
    private String type;
    private BigDecimal daysInMonth;
    private BigDecimal daysInYear;

    public InterestRateDTO(String code, String name, String type, BigDecimal daysInMonth, BigDecimal daysInYear) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.daysInMonth = daysInMonth;
        this.daysInYear = daysInYear;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public BigDecimal getDaysInMonth() {
        return daysInMonth;
    }
    public void setDaysInMonth(BigDecimal daysInMonth) {
        this.daysInMonth = daysInMonth;
    }
    public BigDecimal getDaysInYear() {
        return daysInYear;
    }
    public void setDaysInYear(BigDecimal daysInYear) {
        this.daysInYear = daysInYear;
    }
}
