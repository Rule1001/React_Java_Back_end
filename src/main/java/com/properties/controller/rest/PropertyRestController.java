package com.properties.controller.rest;

import com.properties.model.Property;
import com.properties.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletResponse;
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

    @CrossOrigin("*")
    @RequestMapping(value="/properties/{propertyId}", method = RequestMethod.POST)
    public void saveProperty(@Valid @RequestBody Property property, HttpServletResponse response ) {

        propertyRepository.save(property);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/properties/{propertyId}", method = RequestMethod.DELETE)
    public void deleteProperty(@PathVariable Long propertyId, HttpServletResponse response) {
        System.out.println("Fetching & Deleting Property with propertyId " + propertyId);

        Property property = propertyRepository.findOne(propertyId);
        if (property == null) {
            System.out.println("Unable to delete. Property with propertyID " + propertyId + " not found");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } else {
            propertyRepository.delete(property);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);

        }
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/properties", method = RequestMethod.DELETE)
    public void deleteAllProperties() {
        System.out.println("Deleting All Users");

        propertyRepository.deleteAll();
    }

}
