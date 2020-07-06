package com.wulf.systems.mes.controller;

import com.wulf.systems.mes.entity.Order;
import com.wulf.systems.mes.service.OrderService;
import com.wulf.systems.mes.service.ValidationErrorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ValidationErrorService validationErrorService;
    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAll();
    }
    //If you want to add more info about api
    //@ApiOperation(value = "Find Order by id",notes = "search by id in db",response = Order.class)
    @GetMapping(value = "/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody Order order,BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors!=null)
            return errors;
        return new ResponseEntity<>(orderService.addOrUpdate(order),HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody Order order, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors!=null)
            return errors;
        order.setId(id);
        return new ResponseEntity<>(orderService.addOrUpdate(order), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        orderService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
