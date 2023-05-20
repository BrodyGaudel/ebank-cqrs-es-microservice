package com.brodygaudel.gestionclients.query.mappers.implementation;

import com.brodygaudel.gestionclients.common.dtos.CustomerDTO;
import com.brodygaudel.gestionclients.query.entities.Customer;
import com.brodygaudel.gestionclients.query.mappers.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public CustomerDTO fromCustomer(Customer customer) {
        try{
            return new CustomerDTO(
                    customer.getId(),
                    customer.getFirstname(),
                    customer.getName(),
                    customer.getPlaceOfBirth(),
                    customer.getDateOfBirth(),
                    customer.getNationality(),
                    customer.getCin()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<CustomerDTO> fromListOfCustomers(List<Customer> customers) {
        try{
            return customers.stream().map(this::fromCustomer).toList();
        }catch (Exception e){
            return null;
        }
    }
}
