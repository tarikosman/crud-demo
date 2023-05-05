package com.example.backend.product.persistence;

import javax.persistence.Entity;

import com.example.backend.util.jpa.JPAEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Product extends JPAEntity {
    private String name;
}
