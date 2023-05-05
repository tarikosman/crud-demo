package com.example.backend.product.rest;

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

import com.example.backend.product.persistence.ProductCreateEvent;
import com.example.backend.product.persistence.ProductData;
import com.example.backend.product.persistence.ProductService;
import com.example.backend.product.persistence.ProductUpdateEvent;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductData> createEntity(
            @RequestBody @Validated ProductCreateEvent event) {
        var entity = service.createEntity(event);
        return ResponseEntity.created(URI.create("/api/products/" + entity.getId()))
                .body(entity);
    }

    @GetMapping
    public ResponseEntity<List<ProductData>> getAllEntities() {
        List<ProductData> productData = service.getAllEntities();
        return ResponseEntity.ok(productData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductData> getEntity(@PathVariable UUID id) {
        ProductData productData = service.getEntity(id);
        return ResponseEntity.ok(productData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductData> updateEntity(@PathVariable UUID id,
            @RequestBody @Validated ProductUpdateEvent event) {
        ProductData productData = service.updateEntity(id, event);
        return ResponseEntity.ok(productData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable UUID id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
