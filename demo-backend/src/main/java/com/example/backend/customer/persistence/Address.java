package com.example.backend.customer.persistence;

import javax.persistence.Entity;

import com.example.backend.util.jpa.JPAEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
class Address extends JPAEntity {
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
}
