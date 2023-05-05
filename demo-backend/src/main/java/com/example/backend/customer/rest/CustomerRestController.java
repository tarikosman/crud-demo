package com.example.backend.customer.rest;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.customer.persistence.CustomerCreateEvent;
import com.example.backend.customer.persistence.CustomerData;
import com.example.backend.customer.persistence.CustomerService;
import com.example.backend.customer.persistence.CustomerUpdateEvent;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerData> createEntity(
            @RequestBody @Validated CustomerCreateEvent event) {
        var entity = service.createEntity(event);
        return ResponseEntity.created(URI.create("/api/customers/" + entity.getId()))
                .body(entity);
    }

    @GetMapping
    public ResponseEntity<List<CustomerData>> getAllEntities() {
        List<CustomerData> customerData = service.getAllEntities();
        return ResponseEntity.ok(customerData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerData> getEntity(@PathVariable UUID id) {
        CustomerData customerData = service.getEntity(id);
        return ResponseEntity.ok(customerData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerData> updateEntity(@PathVariable UUID id,
            @RequestBody @Validated CustomerUpdateEvent event) {
        CustomerData customerData = service.updateEntity(id, event);
        return ResponseEntity.ok(customerData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable UUID id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
