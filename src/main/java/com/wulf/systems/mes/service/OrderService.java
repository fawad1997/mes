package com.wulf.systems.mes.service;

import com.wulf.systems.mes.entity.Order;
import com.wulf.systems.mes.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    public Order updateOrder(int id, Order order) {
        order.setId(id);
        orderRepository.save(order);
        return order;
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
