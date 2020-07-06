package com.wulf.systems.mes.controller;


import com.wulf.systems.mes.entity.Workstation;
import com.wulf.systems.mes.service.ValidationErrorService;
import com.wulf.systems.mes.service.WorkstationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workstation")
public class WorkstationController {
    @Autowired
    private WorkstationService workstationService;
    @Autowired
    private ValidationErrorService validationErrorService;
    @GetMapping
    public List<Workstation> getWorkstations() {
        return workstationService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Workstation getWorkstation(@PathVariable int id) {
        return workstationService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addWorkstation(@RequestBody Workstation workstation, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors!=null)
            return errors;
        return new ResponseEntity<>(workstationService.addOrUpdate(workstation), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateWorkstation(@PathVariable int id, @RequestBody Workstation workstation, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors!=null)
            return errors;
        workstation.setId(id);
        return new ResponseEntity<>(workstationService.addOrUpdate(workstation), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteWorkstation(@PathVariable int id) {
        workstationService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}