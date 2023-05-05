package com.example.backend.util.common;

import java.util.UUID;

import lombok.Data;

@Data
public class IdentifiableDataObject implements Identifiable {
    private UUID id;
}
