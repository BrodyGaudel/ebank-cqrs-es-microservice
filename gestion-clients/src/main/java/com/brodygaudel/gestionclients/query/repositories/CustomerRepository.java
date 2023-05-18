package com.brodygaudel.gestionclients.query.repositories;

import com.brodygaudel.gestionclients.query.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("select c from Customer c where c.cin=?1")
    Customer findByCin(String cin);

    @Query("select c from Customer c where c.name like :kw or c.firstname like :kw")
    List<Customer> search(@Param("kw") String kw);

    @Query("select case when count(c)>0 then true else false END from Customer c where c.cin=?1")
    Boolean checkIfCinExists();
}
