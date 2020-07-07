package com.wulf.systems.mes.repository;

import com.wulf.systems.mes.entity.WorkstationConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkstationConfigurationRepository extends JpaRepository<WorkstationConfiguration, Integer> {
}
