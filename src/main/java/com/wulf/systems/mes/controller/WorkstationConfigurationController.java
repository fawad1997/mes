package com.wulf.systems.mes.controller;

import com.wulf.systems.mes.entity.WorkstationConfiguration;
import com.wulf.systems.mes.service.ValidationErrorService;
import com.wulf.systems.mes.service.WorkstationConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workstationConfiguration")
public class WorkstationConfigurationController {

    @Autowired
    private WorkstationConfigurationService workstationConfigurationService;
    @Autowired
    private ValidationErrorService validationErrorService;

    @GetMapping
    public List<WorkstationConfiguration> getWorkstationConfigurations() {
        return workstationConfigurationService.findAll();
    }

    @GetMapping(value = "/{id}")
    public WorkstationConfiguration getWorkstationConfiguration(@PathVariable int id) {
        return workstationConfigurationService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> addWorkstationConfiguration(@RequestBody WorkstationConfiguration workstationConfiguration, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if (errors != null) {
            return errors;
        }
        return new ResponseEntity<>(workstationConfigurationService.addOrUpdate(workstationConfiguration), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateWorkstationConfiguration(@PathVariable int id, @RequestBody WorkstationConfiguration workstationConfiguration, BindingResult result) {
        ResponseEntity errors = validationErrorService.validate(result);
        if (errors != null) {
            return errors;
        }
        workstationConfiguration.setWorkstationConfigurationId(id);
        return new ResponseEntity<>(workstationConfigurationService.addOrUpdate(workstationConfiguration), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteWorkstationConfiguration(@PathVariable int id) {
        workstationConfigurationService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
