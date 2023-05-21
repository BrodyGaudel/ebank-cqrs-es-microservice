package com.brodygaudel.gestioncomptes.query.repositories;

import com.brodygaudel.gestioncomptes.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("select a from Account a where a.customerId=?1")
    Account findByCustomerId(String customerId);

    @Query("select case when count(a)>0 then true else false END from Account a where a.customerId=?1")
    Boolean checkIfCustomerHaveAccount(String customerId);
}
