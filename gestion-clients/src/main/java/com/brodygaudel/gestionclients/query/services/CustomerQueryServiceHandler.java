package com.brodygaudel.gestionclients.query.services;

import com.brodygaudel.gestionclients.query.entities.Customer;
import com.brodygaudel.gestionclients.query.queries.GetAllCustomerQuery;
import com.brodygaudel.gestionclients.query.queries.GetCustomerByCinQuery;
import com.brodygaudel.gestionclients.query.queries.GetCustomerByIdQuery;
import com.brodygaudel.gestionclients.query.queries.SearchCustomerQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerQueryServiceHandler {

    private final CustomerService customerService;

    public CustomerQueryServiceHandler(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryHandler
    public List<Customer> on(GetAllCustomerQuery query) {
        return customerService.findAll();
    }

    @QueryHandler
    public Customer on(GetCustomerByIdQuery query) {
        return customerService.findById(query.getId());
    }

    @QueryHandler
    public Customer on(GetCustomerByCinQuery query) {
        return customerService.findByCin(query.getCin());
    }

    @QueryHandler
    public List<Customer> on(SearchCustomerQuery query) {
        return customerService.search(query.getKeyword());
    }
}
