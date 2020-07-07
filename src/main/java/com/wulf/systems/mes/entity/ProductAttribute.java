package com.wulf.systems.mes.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity(name = "ProductAttribute")
@Table(name = "mes_product_attribute")
public class ProductAttribute {

    @EmbeddedId
    private ProductAttributeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("attributeId")
    private Attribute attribute;

    @OneToMany(
            targetEntity = OrderProductAttributeValue.class,
            mappedBy = "productAttribute"
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrderProductAttributeValue> orderProductAttributes;

    @OneToMany(
            targetEntity = WorkstationConfiguration.class,
            mappedBy = "productAttribute"
    )
    @LazyCollection(LazyCollectionOption.FALSE)

    private List<WorkstationConfiguration> workstationConfigurations;


    public ProductAttribute(Product product, Attribute attribute) {
        this.id = new ProductAttributeId(product.getId(), attribute.getId());
        this.product = product;
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ProductAttribute that = (ProductAttribute) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(attribute, that.attribute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, attribute);
    }
}
