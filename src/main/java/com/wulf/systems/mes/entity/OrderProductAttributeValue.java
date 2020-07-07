package com.wulf.systems.mes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "mes_order_product_attribute_value")
public class OrderProductAttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private int orderProductAttributeId;

    @JsonProperty("Value")
    private String value;

    @OneToMany(
            targetEntity = OrderProduct.class,
            mappedBy = "orderProductAttribute",
            fetch = FetchType.EAGER
    )
    private List<OrderProduct> orderProducts;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductAttribute productAttribute;
}
