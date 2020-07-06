package com.wulf.systems.mes.repository;

import com.wulf.systems.mes.entity.Workstation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkstationRepository extends JpaRepository<Workstation,Integer> {
}
