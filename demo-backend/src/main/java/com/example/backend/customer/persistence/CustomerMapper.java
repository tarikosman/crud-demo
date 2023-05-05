package com.example.backend.customer.persistence;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.backend.product.persistence.ProductMapper;

@Mapper(componentModel = "spring", uses = { AddressMapper.class, ProductMapper.class })
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerCreateEvent createEvent);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromEvent(CustomerUpdateEvent updateEvent, @MappingTarget Customer entity);

    CustomerData toDataObject(Customer entity);
}