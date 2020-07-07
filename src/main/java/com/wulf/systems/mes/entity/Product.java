package com.wulf.systems.mes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "mes_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @JsonProperty("ID")
    private int id;
    @JsonProperty("Name")
    private String name;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductAttribute> attributes = new ArrayList<>();

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderProduct> orders = new ArrayList<>();

    public void addAttribute(Attribute attribute) {
        ProductAttribute productAttribute = new ProductAttribute(this, attribute);
        attributes.add(productAttribute);
        attribute.getProducts().add(productAttribute);
    }

    public void removeAttribute(Attribute attribute) {
        for (Iterator<ProductAttribute> iterator = attributes.iterator();
             iterator.hasNext(); ) {
            ProductAttribute productAttribute = iterator.next();

            if (productAttribute.getProduct().equals(this) &&
                    productAttribute.getAttribute().equals(attribute)) {
                iterator.remove();
                productAttribute.getAttribute().getProducts().remove(productAttribute);
                productAttribute.setProduct(null);
                productAttribute.setAttribute(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
