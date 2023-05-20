package com.brodygaudel.gestionclients.query.services;

import com.brodygaudel.gestionclients.common.events.CustomerCreatedEvent;
import com.brodygaudel.gestionclients.common.events.CustomerDeletedEvent;
import com.brodygaudel.gestionclients.common.events.CustomerUpdatedEvent;
import com.brodygaudel.gestionclients.query.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerCommandServiceHandler {

    private final CustomerService customerService;

    public CustomerCommandServiceHandler(CustomerService customerService) {
        this.customerService = customerService;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event){
        log.info("CustomerCreatedEvent received");
        Customer customer = Customer.builder()
                .cin(event.getCin())
                .dateOfBirth(event.getDateOfBirth())
                .firstname(event.getFirstname())
                .name(event.getName())
                .nationality(event.getNationality())
                .placeOfBirth(event.getPlaceOfBirth())
                .id(event.getId())
                .build();
        customerService.saveCustomer(customer);
    }

    @EventHandler
    public void on(CustomerUpdatedEvent event){
        log.info("CustomerUpdateEvent received");
        Customer customer = Customer.builder()
                .cin(event.getCin())
                .dateOfBirth(event.getDateOfBirth())
                .firstname(event.getFirstname())
                .name(event.getName())
                .nationality(event.getNationality())
                .placeOfBirth(event.getPlaceOfBirth())
                .id(event.getId())
                .build();
        customerService.updateCustomer(customer);
    }

    @EventHandler
    public void on(CustomerDeletedEvent event){
        log.info("CustomerDeletedEvent received");
        customerService.deleteCustomer(event.getId());
    }


}
