package com.example.backend.customer.persistence;

import java.util.List;
import java.util.UUID;

import com.example.backend.product.persistence.ProductData;
import com.example.backend.util.common.Identifiable;

import lombok.Data;

@Data
public class CustomerData implements Identifiable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressData> addresses;
    private List<ProductData> products;
}
