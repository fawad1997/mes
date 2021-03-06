package com.wulf.systems.mes.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "mes_workstation")
public class Workstation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private int id;
    private String name;

    @OneToMany(
            targetEntity = OrderProduct.class,
            mappedBy = "workstation",
            fetch = FetchType.EAGER
    )
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private WorkstationConfiguration workstationConfiguration;
}
