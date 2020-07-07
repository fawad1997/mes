package com.wulf.systems.mes.service;

import com.wulf.systems.mes.entity.WorkstationConfiguration;
import com.wulf.systems.mes.exception.GenericIdException;
import com.wulf.systems.mes.repository.WorkstationConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkstationConfigurationService {

    @Autowired
    private WorkstationConfigurationRepository workstationConfigurationRepository;

    public List<WorkstationConfiguration> findAll() {
        return workstationConfigurationRepository.findAll();
    }

    public WorkstationConfiguration findById(int id) {
        Optional<WorkstationConfiguration> workstationConfiguration = workstationConfigurationRepository.findById(id);
        if (workstationConfiguration.isPresent()) {
            return workstationConfiguration.get();
        } else
            throw new GenericIdException("Workstation with id " + id + " does not exists");
    }

    public WorkstationConfiguration addOrUpdate(WorkstationConfiguration workstation) {
        workstationConfigurationRepository.save(workstation);
        return workstation;
    }

    public void deleteById(int id) {
        Optional<WorkstationConfiguration> workstation = workstationConfigurationRepository.findById(id);
        if (workstation.isPresent()) {
            workstationConfigurationRepository.delete(workstation.get());
        } else
            throw new GenericIdException("WorkstationConfiguration with id " + id + " does not exists");
    }
}
