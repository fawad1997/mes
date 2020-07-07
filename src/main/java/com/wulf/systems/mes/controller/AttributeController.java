package com.wulf.systems.mes.controller;


import com.wulf.systems.mes.entity.Attribute;
import com.wulf.systems.mes.service.AttributeService;
import com.wulf.systems.mes.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private ValidationErrorService validationErrorService;

    @GetMapping
    public List<Attribute> getAttributes() {
        return attributeService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Attribute getAttribute(@PathVariable int id) {
        return attributeService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addAttribute(@RequestBody Attribute attribute, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if (errors != null) {
            return errors;
        }

        return new ResponseEntity<>(attributeService.addOrUpdate(attribute), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAttribute(@PathVariable int id, @RequestBody Attribute attribute, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if (errors != null)
            return errors;
        attribute.setId(id);
        return new ResponseEntity<>(attributeService.addOrUpdate(attribute), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAttribute(@PathVariable int id) {
        attributeService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
