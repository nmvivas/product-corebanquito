package com.banquito.core.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "INTEREST_RATE")
public class InterestRate implements Serializable {

    @Id
    @Column(name = "CODE_INTEREST_RATE", length = 10, nullable = false)
    private String code;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "TYPE", length = 3, nullable = false)
    private String type;

    @Column(name = "DAYS_IN_MONTH", precision = 2, nullable = false)
    private BigDecimal daysInMonth;

    @Column(name = "DAYS_IN_YEAR", precision = 3, nullable = false)
    private BigDecimal daysInYear;

    public InterestRate(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
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
        InterestRate other = (InterestRate) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }
}
