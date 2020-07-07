package com.wulf.systems.mes.repository;

import com.wulf.systems.mes.entity.OrderProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductAttributeValueRepository extends JpaRepository<OrderProductAttributeValue, Integer> {
}
