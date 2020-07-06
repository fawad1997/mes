package com.wulf.systems.mes.service;

import com.wulf.systems.mes.entity.Property;
import com.wulf.systems.mes.exception.GenericIdException;
import com.wulf.systems.mes.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(int id) {
        Optional<Property> property = propertyRepository.findById(id);
        if(property.isPresent()){
            return  property.get();
        }else
            throw new GenericIdException("Property with id "+id+" does not exists");
    }

    public Property addOrUpdate(Property property) {
        propertyRepository.save(property);
        return property;
    }

    public void deleteById(int id) {
        Optional<Property> property = propertyRepository.findById(id);
        if(property.isPresent()){
            propertyRepository.delete(property.get());
        }else
            throw new GenericIdException("Property with id "+id+" does not exists");
    }
}