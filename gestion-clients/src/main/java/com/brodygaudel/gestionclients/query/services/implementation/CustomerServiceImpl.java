package com.brodygaudel.gestionclients.query.services.implementation;

import com.brodygaudel.gestionclients.query.entities.Customer;
import com.brodygaudel.gestionclients.query.repositories.CustomerRepository;
import com.brodygaudel.gestionclients.query.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {
        try{
            customerRepository.save(customer);
            log.info("customer saved");
        }catch (Exception e){
            log.error("customer not saved");
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try{
            Customer c = customerRepository.findById(customer.getId()).orElse(null);
            if(c != null){
               c.setName(customer.getName());
               c.setNationality(customer.getNationality());
               c.setFirstname(customer.getFirstname());
               c.setPlaceOfBirth(customer.getPlaceOfBirth());
               c.setDateOfBirth(customer.getDateOfBirth());
               customerRepository.save(customer);
               log.info("customer updated");
            }else {
                log.warn("customer not found");
            }
        }catch (Exception e){
            log.error("customer not updated");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try{
            customerRepository.deleteById(id);
            log.info("customer deleted");
        }catch (Exception e){
            log.error("customer not deleted");
        }
    }

    @Override
    public Customer findById(String id) {
        try{
            Customer customer = customerRepository.findById(id).orElse(null);
            if(customer == null){
                log.warn("customer not found for id :"+id);
                return null;
            }else{
                log.info("customer well found");
                return customer;
            }
        }catch (Exception e){
            log.error("customer not found :"+e.getMessage());
            return null;
        }
    }

    @Override
    public Customer findByCin(String cin) {
        try{
            Customer customer = customerRepository.findByCin(cin);
            if(customer == null){
                log.warn("customer not found");
                return null;
            }else{
                log.info("customer found");
                return customer;
            }
        }catch (Exception e){
            log.error("customer not found :"+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Customer> findAll() {
        try{
            return customerRepository.findAll();
        }catch (Exception e){
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Customer> search(String keyword) {
        try{
            return customerRepository.search(keyword);
        }catch (Exception e){
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
