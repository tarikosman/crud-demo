package com.example.backend.product.persistence;

import java.util.UUID;

import com.example.backend.util.common.Identifiable;

import lombok.Data;

@Data
public class ProductData implements Identifiable {
    private UUID id;
    private String name;
}
