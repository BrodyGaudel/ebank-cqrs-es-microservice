package com.brodygaudel.gestionclients.query.mappers;

import com.brodygaudel.gestionclients.common.dtos.CustomerDTO;
import com.brodygaudel.gestionclients.query.entities.Customer;

import java.util.List;

public interface Mappers {
    CustomerDTO fromCustomer(Customer customer);
    List<CustomerDTO> fromListOfCustomers(List<Customer> customers);
}
