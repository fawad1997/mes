package com.wulf.systems.mes.repository;

import com.wulf.systems.mes.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Integer> {
}
