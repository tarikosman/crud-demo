package com.example.backend.product.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend.util.jpa.EntityNotFoundException;
import com.example.backend.util.jpa.JpaCollectionUtil;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Autowired
    public ProductRepository productRepository;

    @Mapping(target = "id", ignore = true)
    public abstract Product toEntity(ProductCreateEvent event);

    public Product toEntity(ProductReference reference) {
        return productRepository.findById(reference.getId())
                .orElseThrow(() -> new EntityNotFoundException(reference.getId()));
    }

    @Mapping(target = "id", ignore = true)
    public abstract void updateEntityFromEvent(ProductUpdateEvent event, @MappingTarget Product entity);

    public abstract ProductData toDataObject(Product entity);

    public void updateEntitiesFromReferences(List<ProductReference> references,
            @MappingTarget List<Product> entities) {
        JpaCollectionUtil.shallowUpdateEntitiesFromEvents(
                references, entities, productRepository::getReferenceById);
    }

    public List<ProductData> toDataObjectList(List<Product> entities) {
        return entities.stream().map(this::toDataObject).collect(Collectors.toList());
    }
}
