package org.springframework.data.jpa.example.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    
    public String getCity() {
        return city;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
}
