package com.example.backend.customer.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.example.backend.product.persistence.Product;
import com.example.backend.util.jpa.JPAEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
class Customer extends JPAEntity {

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Address> addresses;

    @ManyToMany
    private List<Product> products;
}
