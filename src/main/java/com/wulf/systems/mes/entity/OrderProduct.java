package com.wulf.systems.mes.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity(name = "OrderProduct")
@Table(name = "mes_order_attribute")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Workstation workstation;

    @ManyToOne(fetch = FetchType.EAGER)
    private OrderProductAttributeValue orderProductAttribute;

    private int quantity;

    private String status;

    public OrderProduct(Order order, Product product) {
        this.id = new OrderProductId(order.getId(), product.getId());
        this.order = order;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderProduct that = (OrderProduct) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
