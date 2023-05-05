package com.example.backend.customer.persistence;

import java.util.List;

import com.example.backend.product.persistence.ProductReference;

import lombok.Data;

@Data
public class CustomerUpdateEvent {
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressUpdateEvent> addresses;
    private List<ProductReference> products;
}
