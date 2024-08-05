package com.banquito.core.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Builder;

@Builder
public class InterestRateLogDTO {
    private Integer id;
    private String codeInterestRate;
    private BigDecimal value;
    private Date startDate;
    private Date endDate;
    private String state;
    
    public InterestRateLogDTO(Integer id, String codeInterestRate, BigDecimal value, Date startDate, Date endDate,
            String state) {
        this.id = id;
        this.codeInterestRate = codeInterestRate;
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCodeInterestRate() {
        return codeInterestRate;
    }
    public void setCodeInterestRate(String codeInterestRate) {
        this.codeInterestRate = codeInterestRate;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
