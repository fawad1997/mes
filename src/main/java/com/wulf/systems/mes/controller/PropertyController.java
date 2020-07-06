package com.wulf.systems.mes.controller;


import com.wulf.systems.mes.entity.Property;
import com.wulf.systems.mes.service.PropertyService;
import com.wulf.systems.mes.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private ValidationErrorService validationErrorService;
    @GetMapping
    public List<Property> getProperties() {
        return propertyService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Property getProperty(@PathVariable int id) {
        return propertyService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addProperty(@RequestBody Property property, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors!=null)
            return errors;
        return new ResponseEntity<>(propertyService.addOrUpdate(property), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable int id, @RequestBody Property property, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors!=null)
            return errors;
        property.setId(id);
        return new ResponseEntity<>(propertyService.addOrUpdate(property), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable int id) {
        propertyService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}