package com.brodygaudel.gestionclients.query.restcontroller;

import com.brodygaudel.gestionclients.common.dtos.CustomerDTO;
import com.brodygaudel.gestionclients.query.entities.Customer;
import com.brodygaudel.gestionclients.query.mappers.Mappers;
import com.brodygaudel.gestionclients.query.queries.GetAllCustomerQuery;
import com.brodygaudel.gestionclients.query.queries.GetCustomerByCinQuery;
import com.brodygaudel.gestionclients.query.queries.GetCustomerByIdQuery;
import com.brodygaudel.gestionclients.query.queries.SearchCustomerQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/queries")
public class QueryRestController {

    private final QueryGateway queryGateway;
    private final Mappers mappers;

    public QueryRestController(QueryGateway queryGateway, Mappers mappers) {
        this.queryGateway = queryGateway;
        this.mappers = mappers;
    }


    @GetMapping("/list")
    public List<CustomerDTO> customerList() {
        List<Customer> customers = queryGateway.query(new GetAllCustomerQuery(), ResponseTypes.multipleInstancesOf(Customer.class)).join();
        return mappers.fromListOfCustomers(customers);
    }


    @GetMapping("/get/{id}")
    public CustomerDTO getCustomerById(@PathVariable String id) {
        Customer customer = queryGateway.query(new GetCustomerByIdQuery(id), ResponseTypes.instanceOf(Customer.class)).join();
        return mappers.fromCustomer(customer);
    }

    @GetMapping("/find/{cin}")
    public CustomerDTO getCustomerByCin(@PathVariable String cin) {
        Customer customer = queryGateway.query(new GetCustomerByCinQuery(cin), ResponseTypes.instanceOf(Customer.class)).join();
        return mappers.fromCustomer(customer);
    }

    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<Customer> customers = queryGateway.query(new SearchCustomerQuery(keyword), ResponseTypes.multipleInstancesOf(Customer.class)).join();
        return mappers.fromListOfCustomers(customers);
    }
}
