package com.example.backend.customer.persistence;

import lombok.Data;

/**
 * Addresses are created as cascade from the Company and must not have an ID at
 * creation time.
 */
@Data
public class AddressCreateEvent {
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
}
