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
        Assert.assertEquals(200, result.getResponse().getStatus());
    }
}

