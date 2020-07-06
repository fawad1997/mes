package com.wulf.systems.mes.service;


import com.wulf.systems.mes.entity.Attribute;
import com.wulf.systems.mes.exception.GenericIdException;

import com.wulf.systems.mes.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;

    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    public Attribute findById(int id) {
        Optional<Attribute> attribute = attributeRepository.findById(id);
        if(attribute.isPresent()){
            return  attribute.get();
        }else
            throw new GenericIdException("Attribute with id "+id+" does not exists");
    }

    public Attribute addOrUpdate(Attribute attribute) {
        attributeRepository.save(attribute);
        return attribute;
    }

    public void deleteById(int id) {
        Optional<Attribute> attribute = attributeRepository.findById(id);
        if(attribute.isPresent()){
            attributeRepository.delete(attribute.get());
        }else
            throw new GenericIdException("Attribute with id "+id+" does not exists");
    }
}
