package com.example.backend.customer.persistence;

import java.util.UUID;

import com.example.backend.util.common.Identifiable;

import lombok.Data;

@Data
public class AddressData implements Identifiable {
    private UUID id;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
}
