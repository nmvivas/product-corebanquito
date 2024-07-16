package com.banquito.core.product.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class ProductPK implements Serializable {

    @Column(name = "CODE_PRODUCT_TYPE", length = 20, nullable = false)
    private String codeProductType;

    @Column(name = "CODE_PRODUCT", length = 30, nullable = false)
    private String code;

    public ProductPK(String codeProductType, String code) {
        this.codeProductType = codeProductType;
        this.code = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codeProductType == null) ? 0 : codeProductType.hashCode());
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
        ProductPK other = (ProductPK) obj;
        if (codeProductType == null) {
            if (other.codeProductType != null)
                return false;
        } else if (!codeProductType.equals(other.codeProductType))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }
}
