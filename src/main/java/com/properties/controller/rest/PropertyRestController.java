package com.properties.controller.rest;

import com.properties.model.Property;
import com.properties.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.Valid;
import java.util.List;


@RestController
public class PropertyRestController {


    @Autowired
    protected PropertyRepository propertyRepository;

    @CrossOrigin("*")
    @RequestMapping(value="/properties", method = RequestMethod.GET)
    public List<Property> getAllProperties() { return (List<Property>)propertyRepository.findAll(); }

    @CrossOrigin("*")
    @RequestMapping(value="/properties/{propertyId}", method = RequestMethod.GET)
    public Property getIndividulaProperty(@PathVariable Long propertyId) {
        Property property = propertyRepository.findOne(propertyId);

        return property;
    }
    
}
