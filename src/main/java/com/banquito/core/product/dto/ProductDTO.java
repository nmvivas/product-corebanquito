package com.banquito.core.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.banquito.core.product.model.ProductPK;


public class ProductDTO {
    private ProductPK pk;
    private String codeInterestRate;
    private String codeSegment;
    private String name;
    private String state;
    private Date startDate;
    private Date endDate;
    private String allowDebitCard;
    private String allowTransferences;
    private BigDecimal minOpeningBalance;
    
    public ProductDTO(ProductPK pk, String codeInterestRate, String codeSegment, String name, String state,
            Date startDate, Date endDate, String allowDebitCard, String allowTransferences,
            BigDecimal minOpeningBalance) {
        this.pk = pk;
        this.codeInterestRate = codeInterestRate;
        this.codeSegment = codeSegment;
        this.name = name;
        this.state = state;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allowDebitCard = allowDebitCard;
        this.allowTransferences = allowTransferences;
        this.minOpeningBalance = minOpeningBalance;
    }
    public ProductPK getPk() {
        return pk;
    }
    public void setPk(ProductPK pk) {
        this.pk = pk;
    }
    public String getCodeInterestRate() {
        return codeInterestRate;
    }
    public void setCodeInterestRate(String codeInterestRate) {
        this.codeInterestRate = codeInterestRate;
    }
    public String getCodeSegment() {
        return codeSegment;
    }
    public void setCodeSegment(String codeSegment) {
        this.codeSegment = codeSegment;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
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
    public String getAllowDebitCard() {
        return allowDebitCard;
    }
    public void setAllowDebitCard(String allowDebitCard) {
        this.allowDebitCard = allowDebitCard;
    }
    public String getAllowTransferences() {
        return allowTransferences;
    }
    public void setAllowTransferences(String allowTransferences) {
        this.allowTransferences = allowTransferences;
    }
    public BigDecimal getMinOpeningBalance() {
        return minOpeningBalance;
    }
    public void setMinOpeningBalance(BigDecimal minOpeningBalance) {
        this.minOpeningBalance = minOpeningBalance;
    }
}
