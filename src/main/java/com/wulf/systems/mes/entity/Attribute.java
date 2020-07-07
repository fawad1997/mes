package com.wulf.systems.mes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "mes_attribute")
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)

public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    @Column(name = "attribute_id")
    private int id;
    private String name;
    @JsonProperty("Attribute_Type")
    private String attributeType;

    @OneToMany(
            mappedBy = "attribute",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductAttribute> products = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "attribute")
    private List<Property> properties = new ArrayList<>();

    public void addProperty(Property property) {
        properties.add(property);
        property.setAttribute(this);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
        property.setAttribute(null);
    }
}
