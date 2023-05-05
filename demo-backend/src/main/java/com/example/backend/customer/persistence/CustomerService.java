package com.example.backend.customer.persistence;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.backend.util.jpa.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerData createEntity(CustomerCreateEvent event) {
        var entity = customerMapper.toEntity(event);
        entity = customerRepository.save(entity);
        return customerMapper.toDataObject(entity);
    }

    public CustomerData updateEntity(UUID id, CustomerUpdateEvent updateEvent) {
        var entity = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        customerMapper.updateEntityFromEvent(updateEvent, entity);
        entity = customerRepository.save(entity);
        return customerMapper.toDataObject(entity);
    }

    public void deleteEntity(UUID id) {
        customerRepository.deleteById(id);
    }

    public CustomerData getEntity(UUID id) {
        var entity = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return customerMapper.toDataObject(entity);
    }

    public List<CustomerData> getAllEntities() {
        return customerRepository.findAll()
                .stream().map(customerMapper::toDataObject)
                .toList();
    }
}
