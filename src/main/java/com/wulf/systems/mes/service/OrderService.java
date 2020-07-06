package com.wulf.systems.mes.service;

import com.wulf.systems.mes.entity.Order;
import com.wulf.systems.mes.exception.GenericIdException;
import com.wulf.systems.mes.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
           return  order.get();
        }else
            throw new GenericIdException("Order with id "+id+" does not exists");
    }

    public Order addOrUpdate(Order order) {
        orderRepository.save(order);
        return order;
    }

    public void deleteById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.delete(order.get());
        }else
            throw new GenericIdException("Order with id "+id+" does not exists");
    }
}
