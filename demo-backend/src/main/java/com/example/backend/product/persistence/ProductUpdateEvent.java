package com.example.backend.product.persistence;

import java.util.UUID;

import com.example.backend.util.common.Identifiable;

import lombok.Data;

@Data
public class ProductUpdateEvent implements Identifiable {
    private UUID id;
    private String name;
}
