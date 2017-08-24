package com.properties.controller.rest;

import com.properties.model.Property;
import com.properties.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@RestController
public class PropertyRestController {

    private final Logger logger = LoggerFactory.getLogger(PropertyRestController.class);


    @Autowired
    protected PropertyRepository propertyRepository;

    //get all properties
    @CrossOrigin("*")
    @RequestMapping(value="/properties", method = RequestMethod.GET)
    public List<Property> getAllProperties() {

        List<Property> properties = (List<Property>)propertyRepository.findAll();

        logger.debug("get all properties {}", properties);

        return properties;

    }

    //get a property
    @CrossOrigin("*")
    @RequestMapping(value="/properties/{propertyId}", method = RequestMethod.GET)
    public Property getIndividulaProperty(@PathVariable Long propertyId) {

        logger.debug("get individual property STARTED");

        Property property = propertyRepository.findOne(propertyId);

        if(property == null) {
            logger.warn("property {} does not exist", propertyId);
        }

        logger.debug("get individual property ENDED");

        return property;
    }

    //add a property
    @CrossOrigin("*")
    @RequestMapping(value="/properties/{propertyId}", method = RequestMethod.POST)
    public void saveProperty(@Valid @RequestBody Property property, HttpServletResponse response ) {

        logger.debug("property add has STARTED");

        propertyRepository.save(property);

        logger.debug("new property succesfully added");

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    //delete a property
    @CrossOrigin("*")
    @RequestMapping(value = "/properties/{propertyId}", method = RequestMethod.DELETE)
    public void deleteProperty(@PathVariable Long propertyId, HttpServletResponse response) {

        logger.debug("Fetching & Deleting Property with propertyId {}", propertyId);

        Property property = propertyRepository.findOne(propertyId);
        if (property == null) {
            logger.debug("Unable to delete. Property with propertyID {} not found", propertyId);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } else {
            propertyRepository.delete(property);
            logger.debug("property deleted");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);

        }
    }
    //delete all properties
    @CrossOrigin("*")
    @RequestMapping(value = "/properties", method = RequestMethod.DELETE)
    public void deleteAllProperties() {
        System.out.println("Deleting All Users");

        propertyRepository.deleteAll();
    }

    //update property
    @CrossOrigin("*")
    @RequestMapping(value = "/properties/{propertyId}", method = RequestMethod.PUT)
    public void updateProperty(@PathVariable Long propertyId, @RequestBody Property property, HttpServletResponse response) {
        System.out.println("Updating Property " + propertyId);

        propertyRepository.findOne(propertyId);

        if (property==null) {
            System.out.println("Property with id " + propertyId + " not found");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        property.setPropertyId(property.getPropertyId());
        property.setPropertyType(property.getPropertyType());
        property.setLocation(property.getLocation());
        property.setNumBedrooms(property.getNumBedrooms());

        propertyRepository.save(property);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }



}
