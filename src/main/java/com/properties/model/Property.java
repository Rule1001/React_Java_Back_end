package com.properties.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Entity
public class Property {

    private Long propertyId = null;
    private String propertyType = null;
    private int bedrooms;
    private String location = null;
    private boolean forSale = false;

 

}