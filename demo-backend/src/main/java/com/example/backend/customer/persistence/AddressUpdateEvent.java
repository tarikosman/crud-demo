package com.example.backend.customer.persistence;

import java.util.UUID;

import com.example.backend.util.common.IdentifiableDataObject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Addresses are updated as cascade from the Customer and therefore have an ID.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AddressUpdateEvent extends IdentifiableDataObject {
    private UUID id;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
}
