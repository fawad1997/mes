package com.wulf.systems.mes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
public class ProductAttributeId implements Serializable {

    @Column(name = "product_id")
    private int productId;

    @Column(name = "attribute_id")
    private int attribute_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProductAttributeId that = (ProductAttributeId) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(attribute_id, that.attribute_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, attribute_id);
    }
}
