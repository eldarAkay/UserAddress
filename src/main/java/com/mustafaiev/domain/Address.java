package com.mustafaiev.domain;

/**
 * Created by user on 09.10.2015.
 */
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents the Address entity
 *
 * @author Eldar Mustafaiev
 */
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @NotEmpty
    @Column(name = "REGION")
    private String region;

    @NotEmpty
    @Column(name = "CITY")
    private String city;

    @NotEmpty
    @Column(name = "STREET")
    private String street;

    @NotEmpty
    @Column(name = "HOME_NUMBER")
    private String homeNumber;

   // @Pattern(regexp="[0-9]+")
    @Column(name = "FLAT_NUMBER")
    private Integer flatNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }
}