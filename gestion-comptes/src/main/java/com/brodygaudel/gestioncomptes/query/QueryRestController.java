package com.brodygaudel.gestioncomptes.query;

import com.brodygaudel.gestioncomptes.common.dtos.AccountResponseDTO;
import com.brodygaudel.gestioncomptes.query.entities.Account;
import com.brodygaudel.gestioncomptes.query.mapping.Mappers;
import com.brodygaudel.gestioncomptes.query.queries.GetAccountByCustomerIdQuery;
import com.brodygaudel.gestioncomptes.query.queries.GetAccountByIdQuery;
import com.brodygaudel.gestioncomptes.query.queries.GetAllAccountQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<AccountResponseDTO> accountList() {
        List<Account> accounts = queryGateway.query(new GetAllAccountQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
        return mappers.fromListOfAccounts(accounts);
    }


    @GetMapping("/get/{id}")
    public AccountResponseDTO getAccountById(@PathVariable String id) {
        Account account = queryGateway.query(new GetAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class)).join();
        return mappers.fromAccount(account);
    }

    @GetMapping("/find/{customerId}")
    public AccountResponseDTO getCustomerByCin(@PathVariable String customerId) {
        Account account = queryGateway.query(new GetAccountByCustomerIdQuery(customerId), ResponseTypes.instanceOf(Account.class)).join();
        return mappers.fromAccount(account);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
