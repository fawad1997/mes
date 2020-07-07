package com.wulf.systems.mes.service;

import com.wulf.systems.mes.entity.OrderProductAttributeValue;
import com.wulf.systems.mes.exception.GenericIdException;
import com.wulf.systems.mes.repository.OrderProductAttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductAttributeValueService {

    @Autowired
    private OrderProductAttributeValueRepository orderProductAttributeValueRepository;

    public List<OrderProductAttributeValue> findAll() {
        return orderProductAttributeValueRepository.findAll();
    }

    public OrderProductAttributeValue findById(int id) {
        Optional<OrderProductAttributeValue> orderProductAttributeValue = orderProductAttributeValueRepository.findById(id);
        if (orderProductAttributeValue.isPresent()) {
            return orderProductAttributeValue.get();
        } else
            throw new GenericIdException("Workstation with id " + id + " does not exists");
    }

    public OrderProductAttributeValue addOrUpdate(OrderProductAttributeValue orderProductAttributeValue) {
        orderProductAttributeValueRepository.save(orderProductAttributeValue);
        return orderProductAttributeValue;
    }

    public void deleteById(int id) {
        Optional<OrderProductAttributeValue> orderProductAttributeValue = orderProductAttributeValueRepository.findById(id);
        if (orderProductAttributeValue.isPresent()) {
            orderProductAttributeValueRepository.delete(orderProductAttributeValue.get());
        } else
            throw new GenericIdException("OrderProductAttributeValue with id " + id + " does not exists");
    }
}
