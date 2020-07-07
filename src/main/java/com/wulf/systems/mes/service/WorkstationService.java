package com.wulf.systems.mes.service;

import com.wulf.systems.mes.entity.Workstation;
import com.wulf.systems.mes.exception.GenericIdException;
import com.wulf.systems.mes.repository.WorkstationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkstationService {
    @Autowired
    private WorkstationRepository workstationRepository;

    public List<Workstation> findAll() {
        return workstationRepository.findAll();
    }

    public Workstation findById(int id) {
        Optional<Workstation> workstation = workstationRepository.findById(id);
        if (workstation.isPresent()) {
            return workstation.get();
        } else
            throw new GenericIdException("Workstation with id " + id + " does not exists");
    }

    public Workstation addOrUpdate(Workstation workstation) {
        workstationRepository.save(workstation);
        return workstation;
    }

    public void deleteById(int id) {
        Optional<Workstation> workstation = workstationRepository.findById(id);
        if (workstation.isPresent()) {
            workstationRepository.delete(workstation.get());
        } else
            throw new GenericIdException("WorkStation with id " + id + " does not exists");
    }
}
