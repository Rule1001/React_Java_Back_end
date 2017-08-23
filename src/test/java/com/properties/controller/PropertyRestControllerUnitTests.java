package com.properties.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.properties.controller.rest.PropertyRestController;
import com.properties.model.Property;
import com.properties.repository.PropertyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@RunWith(SpringRunner.class)
@WebMvcTest(value = PropertyRestController.class)

public class PropertyRestControllerUnitTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyRepository propertyRepository;

    @Test
    public void showIndividualProperty() throws Exception {
        Property property = new Property();
        property.setPropertyId(1L);
        property.setPropertyType("House");
        property.setNumBedrooms(3);
        property.setLocation("Sale");


        Mockito.when(propertyRepository.findOne(Mockito.anyLong())).thenReturn(property);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{propertyId:1, propertyType:House, numBedrooms:3, location:Sale}";

        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void showAllProperties() throws Exception {

        Property property1 = new Property();
        property1.setPropertyId(1L);
        property1.setPropertyType("House");
        property1.setNumBedrooms(3);
        property1.setLocation("Sale");

        Property property2 = new Property();
        property2.setPropertyId(2L);
        property2.setPropertyType("Apartment");
        property2.setNumBedrooms(1);
        property2.setLocation("Manchester");

        List<Property> property = new ArrayList<Property>();

        property.add(property1);
        property.add(property2);


        Mockito.when(propertyRepository.findAll()).thenReturn(property);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"propertyId\":1,\"propertyType\":\"House\",\"location\":\"Sale\",\"numBedrooms\":3},{\"propertyId\":2,\"propertyType\":\"Apartment\",\"location\":\"Manchester\",\"numBedrooms\":1}]";

        System.out.println(result.getResponse().getContentAsString());

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void saveProperty() throws Exception {
        Property property = new Property();
        property.setPropertyId(1L);
        property.setPropertyType("House");
        property.setNumBedrooms(3);
        property.setLocation("Sale");

        String carJson = new ObjectMapper().writeValueAsString(property);

        Mockito.when(propertyRepository.save(property)).thenReturn(property);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/properties/-1").contentType(MediaType.APPLICATION_JSON).content(carJson);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Mockito.verify(propertyRepository, Mockito.times(1)).save(Mockito.any(Property.class));
        Assert.assertEquals(202, result.getResponse().getStatus());
    }

//    @Test
//    public void deleteProperty() throws Exception {
//        Property property1 = new Property();
//        property1.setPropertyId(1L);
//        property1.setPropertyType("House");
//        property1.setNumBedrooms(3);
//        property1.setLocation("Sale");
//
//        Property property2 = new Property();
//        property2.setPropertyId(2L);
//        property2.setPropertyType("Apartment");
//        property2.setNumBedrooms(1);
//        property2.setLocation("Manchester");
//
//        Mockito.when(propertyRepository.delete(property1));
//
//        propertyRepository();
//        Person person = new Person(1L);
//        Person person2 = new Person(1L);
//
//        when(personRepository.returnPerson(1L)).thenReturn(person2); //expect a fetch, return a "fetched" person;
//
//        personService.deleteFromPerson(person);
//
//        verify(personRepository, times(1)).delete(person2); //pretty sure it is verify after call
//    }
}

