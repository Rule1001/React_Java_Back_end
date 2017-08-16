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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name ="property_id")
    public Long getPropertyId() {
        return propertyId;
    }
    public void setPropertyId(Long propertyId){
        this.propertyId=propertyId;
    }

    @Column(name ="property_type")
    @NotEmpty (message = "You must specify a Property_type")
    public String getPropertyType() {
        return propertyType;
    }
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    

}