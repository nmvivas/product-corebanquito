package com.banquito.core.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @EmbeddedId
    private ProductPK pk;

    @Column(name = "UNIQUE_ID", length = 30, nullable = false)
    private String uniqueId;

    @Column(name = "CODE_INTEREST_RATE", length = 10, nullable = false)
    private String codeInterestRate;

    @Column(name = "CODE_SEGMENT", length = 10, nullable = false)
    private String codeSegment;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "STATE", length = 3, nullable = false)
    private String state;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "ALLOW_DEBIT_CARD", length = 1)
    private String allowDebitCard;

    @Column(name = "ALOOW_TRANSFERENCES", length = 1)
    private String allowTransferences;

    @Column(name = "MIN_OPENING_BALANCE", precision = 17, scale = 2, nullable = false)
    private BigDecimal minOpeningBalance;

    @ManyToOne
    @JoinColumn(name = "CODE_PRODUCT_TYPE", referencedColumnName = "CODE_PRODUCT_TYPE", insertable = false, updatable = false)
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "CODE_INTEREST_RATE", referencedColumnName = "CODE_INTEREST_RATE", insertable = false, updatable = false)
    private InterestRate interestRate;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (pk == null) {
            if (other.pk != null)
                return false;
        } else if (!pk.equals(other.pk))
            return false;
        if (uniqueId == null) {
            if (other.uniqueId != null)
                return false;
        } else if (!uniqueId.equals(other.uniqueId))
            return false;
        return true;
    }


}
