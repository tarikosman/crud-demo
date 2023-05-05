package com.example.backend.customer.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.backend.util.jpa.JpaCollectionUtil;

@Mapper(componentModel = "spring")
interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    Address toEntity(AddressCreateEvent event);

    Address toEntity(AddressUpdateEvent event);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromEvent(AddressUpdateEvent event, @MappingTarget Address entity);

    AddressData toDataObject(Address event);

    default List<Address> toEntities(List<AddressCreateEvent> events) {
        return events.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default void updateAddressesFromEvents(List<AddressUpdateEvent> events,
            @MappingTarget List<Address> entities) {
        JpaCollectionUtil.deepUpdateEntitiesFromEvents(events, entities,
                this::toEntity,
                this::updateEntityFromEvent);
    }

    default List<AddressData> toAddressDataList(List<Address> addresses) {
        return addresses.stream().map(this::toDataObject).collect(Collectors.toList());
    }
}
