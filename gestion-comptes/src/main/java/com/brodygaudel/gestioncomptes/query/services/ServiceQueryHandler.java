package com.brodygaudel.gestioncomptes.query.services;

import com.brodygaudel.gestioncomptes.common.exceptions.AccountNotFoundException;
import com.brodygaudel.gestioncomptes.query.entities.Account;
import com.brodygaudel.gestioncomptes.query.queries.GetAccountByCustomerIdQuery;
import com.brodygaudel.gestioncomptes.query.queries.GetAccountByIdQuery;
import com.brodygaudel.gestioncomptes.query.queries.GetAllAccountQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceQueryHandler {

    private final AccountService service;

    public ServiceQueryHandler(AccountService service) {
        this.service = service;
    }

    @QueryHandler
    public List<Account> on(GetAllAccountQuery query) {
        return service.findAllAccounts();
    }

    @QueryHandler
    public Account on(GetAccountByIdQuery query) throws AccountNotFoundException {
        return service.findAccountById(query.getId());
    }

    @QueryHandler
    public Account on(GetAccountByCustomerIdQuery query) throws AccountNotFoundException {
        return service.findAccountByCustomerId(query.getCustomerId());
    }


}
