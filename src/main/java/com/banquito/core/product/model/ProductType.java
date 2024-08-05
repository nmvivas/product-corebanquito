package com.banquito.core.product.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "PRODUCT_TYPE")
public class ProductType implements Serializable {

    @Id
    @Column(name = "CODE_PRODUCT_TYPE", length = 20, nullable = false)
    private String code;

    @Column(name = "UNIQUE_ID", length = 20, nullable = false)
    private String uniqueId;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "PRODUCT_TYPE", length = 3, nullable = false)
    private String productType;

    @Column(name = "ALLOW_EARN_INTEREST", length = 1, nullable = false)
    private String allowEarnInterest;

    @Column(name = "TEMPORALITY_INTEREST", length = 3)
    private String temporalityInterest;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
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
        ProductType other = (ProductType) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (uniqueId == null) {
            if (other.uniqueId != null)
                return false;
        } else if (!uniqueId.equals(other.uniqueId))
            return false;
        return true;
    }
}
