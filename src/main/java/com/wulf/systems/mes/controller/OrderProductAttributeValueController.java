package com.wulf.systems.mes.controller;

import com.wulf.systems.mes.entity.OrderProductAttributeValue;
import com.wulf.systems.mes.service.OrderProductAttributeValueService;
import com.wulf.systems.mes.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderProductAttributeValue")
public class OrderProductAttributeValueController {

    @Autowired
    private OrderProductAttributeValueService orderProductAttributeValueService;
    @Autowired
    private ValidationErrorService validationErrorService;

    @GetMapping
    public List<OrderProductAttributeValue> getOrderProductAttributeValue() {
        return orderProductAttributeValueService.findAll();
    }

    @GetMapping(value = "/{id}")
    public OrderProductAttributeValue getOrderProductAttributeValue(@PathVariable int id) {
        return orderProductAttributeValueService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addOrderProductAttributeValue(@RequestBody OrderProductAttributeValue orderProductAttributeValue, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if (errors != null) {
            return errors;
        }
        return new ResponseEntity<>(orderProductAttributeValueService.addOrUpdate(orderProductAttributeValue), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateOrderProductAttributeValue(@PathVariable int id, @RequestBody OrderProductAttributeValue orderProductAttributeValue, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if (errors != null) {
            return errors;
        }
        orderProductAttributeValue.setOrderProductAttributeId(id);
        return new ResponseEntity<>(orderProductAttributeValueService.addOrUpdate(orderProductAttributeValue), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOrderProductAttributeValue(@PathVariable int id) {
        orderProductAttributeValueService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
