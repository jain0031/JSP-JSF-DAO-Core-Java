package com.algonquincollege.cst8277.model;
/** File: ContactsController.java*
 *  Author (original): course materials (19W) CST 8277*
 *  Created on February 2019
 *  Author: Student 040-884087 
 *  Description: My Contact class is model class store the values to pass in database that user inputs *
 *  @author :   Vaibhav Jain
 *   */
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Contact implements Serializable{
    

    protected int id;
    protected String street;
    protected String city;
    protected String postal;
    protected String country;
    protected String firstName;
    protected String lastName;
    protected String email;

    /**
     * @return integer id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return a String named street
     */
    public String getStreet() {
        return street;
    }
    /**
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @returna String named city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * @returna String named postal
     */
    public String getPostal() {
        return postal;
    }
    /**
     * @param postal
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }
    
    /**
     * @returna String named country
     */
    public String getCountry() {
        return country;
    }
    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * @returna String named first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return a String named last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * @return a String named email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Contact [id=")
            .append(id)
            .append(", street=")
            .append(street)
            .append(", city=")
            .append(city)
            .append(", postal=")
            .append(postal)
            .append(", country=")
            .append(country)
            .append(", firstName=")
            .append(firstName)
            .append(", lastName=")
            .append(lastName)
            .append(", email=")
            .append(email)
            .append("]");
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }
}