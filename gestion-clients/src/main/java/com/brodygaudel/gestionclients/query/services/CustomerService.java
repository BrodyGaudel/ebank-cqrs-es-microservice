package com.brodygaudel.gestionclients.query.services;

import com.brodygaudel.gestionclients.query.entities.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(String id);

    Customer findById(String id);
    Customer findByCin(String cin);
    List<Customer> findAll();
    List<Customer> search(String keyword);

}
