package com.example.backend.product.persistence;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.backend.util.jpa.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductData createEntity(ProductCreateEvent event) {
        var entity = productMapper.toEntity(event);
        entity = productRepository.save(entity);
        return productMapper.toDataObject(entity);
    }

    public ProductData updateEntity(UUID id, ProductUpdateEvent updateEvent) {
        var entity = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        productMapper.updateEntityFromEvent(updateEvent, entity);
        entity = productRepository.save(entity);
        return productMapper.toDataObject(entity);
    }

    public void deleteEntity(UUID id) {
        productRepository.deleteById(id);
    }

    public ProductData getEntity(UUID id) {
        var entity = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return productMapper.toDataObject(entity);
    }

    public List<ProductData> getAllEntities() {
        return productRepository.findAll()
                .stream().map(productMapper::toDataObject)
                .toList();
    }
}
