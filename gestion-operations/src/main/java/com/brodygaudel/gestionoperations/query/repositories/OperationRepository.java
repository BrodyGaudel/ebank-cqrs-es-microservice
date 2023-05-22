package com.brodygaudel.gestionoperations.query.repositories;

import com.brodygaudel.gestionoperations.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, String> {

    @Query("select o from Operation o where o.accountId=?1")
    List<Operation> findByAccountId(String accountId);
}
