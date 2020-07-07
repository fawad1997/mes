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
@Table(name = "mes_workstation_configuration")
public class WorkstationConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private int workstationConfigurationId;

    @JsonProperty("Permission")
    private String permission;

    @OneToMany(targetEntity = Workstation.class,
    mappedBy = "workstationConfiguration",
    fetch = FetchType.EAGER)
    private List<Workstation> workstations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductAttribute productAttribute;
}
