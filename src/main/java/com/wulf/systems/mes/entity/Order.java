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
@Table(name = "mes_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private int id;
    @JsonProperty("Name")
    private String name;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderProduct> products = new ArrayList<>();

    public void addProduct(Product product) {
        OrderProduct orderProduct = new OrderProduct(this, product);
        products.add(orderProduct);
        product.getOrders().add(orderProduct);
    }

    public void removeProduct(Product product) {
        for (Iterator<OrderProduct> iterator = products.iterator();
             iterator.hasNext(); ) {
            OrderProduct orderProduct = iterator.next();

            if (orderProduct.getProduct().equals(this) &&
                    orderProduct.getProduct().equals(product)) {
                iterator.remove();
                orderProduct.getProduct().getOrders().remove(orderProduct);
                orderProduct.setProduct(null);
                orderProduct.setOrder(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Order order = (Order) o;
        return Objects.equals(name, order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
