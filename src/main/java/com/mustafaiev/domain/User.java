package com.mustafaiev.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Created by user on 09.10.2015.
 */
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * Represents the user entity
 *
 * @author Mustafaiev Eldar
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {
    private static final long serialVersionUID = 5924361831551833717L;

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @NotEmpty
    @Column(name = "FIRST_NAME")
    private  String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME")
    private  String lastName;

    @Column(name = "PATRONYMIC")
    private String patronymic;

    @Pattern(regexp="[0-9]+")
    @Column(name = "PHONE")
    private  String phone;

    @Email
    @Column(name = "EMAIL")
    private  String email;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @ManyToOne
    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
