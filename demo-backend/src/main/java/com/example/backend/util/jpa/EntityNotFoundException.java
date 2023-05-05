package com.example.backend.util.jpa;

import java.util.UUID;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final UUID id;

    public EntityNotFoundException(UUID id) {
        super(String.format("Entity with ID %s not found", id));
        this.id = id;
    }
}
