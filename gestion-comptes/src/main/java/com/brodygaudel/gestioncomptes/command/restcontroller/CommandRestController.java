package com.brodygaudel.gestioncomptes.command.restcontroller;

import com.brodygaudel.gestioncomptes.common.CustomerHaveAccountException;
import com.brodygaudel.gestioncomptes.common.commands.CreateAccountCommand;
import com.brodygaudel.gestioncomptes.common.commands.UpdateAccountCommand;
import com.brodygaudel.gestioncomptes.common.dtos.*;
import com.brodygaudel.gestioncomptes.common.exceptions.AccountNotFoundException;
import com.brodygaudel.gestioncomptes.common.exceptions.CustomerNotFoundException;
import com.brodygaudel.gestioncomptes.common.feign.CustomerFeignClient;
import com.brodygaudel.gestioncomptes.common.rib.GenerateRIB;
import com.brodygaudel.gestioncomptes.query.entities.Account;
import com.brodygaudel.gestioncomptes.query.repositories.AccountRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v3/commands")
public class CommandRestController {

    private final CommandGateway commandGateway;
    private final EventStore eventStore;
    private final CustomerFeignClient customerFeignClient;
    private final GenerateRIB generateRIB;
    private final AccountRepository repository;

    public CommandRestController(CommandGateway commandGateway, EventStore eventStore, CustomerFeignClient customerFeignClient, GenerateRIB generateRIB, AccountRepository repository) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
        this.customerFeignClient = customerFeignClient;
        this.generateRIB = generateRIB;
        this.repository = repository;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) throws CustomerNotFoundException, CustomerHaveAccountException {
        CustomerDTO customerDTO = customerFeignClient.getCustomerById(request.customerId());
        if(customerDTO == null){
            throw new CustomerNotFoundException("Customer with id :"+request.customerId()+" not found");
        }
        Boolean customerIdExist = repository.checkIfCustomerHaveAccount(request.customerId());
        if(Boolean.TRUE.equals(customerIdExist)){
            throw new CustomerHaveAccountException("Customer with id :"+request.customerId()+"already have an account");
        }
        return commandGateway.send( new CreateAccountCommand(
                generateRIB.autoGenerate(),
                request.currency(),
                request.initialBalance(),
                request.customerId()
        ));
    }

    @PutMapping(path = "/update")
    public CompletableFuture<String> updateAccount(@RequestBody UpdateAccountRequestDTO request) throws AccountNotFoundException {
        Account account = repository.findById(request.id()).orElse(null);
        if(account == null){
            throw new AccountNotFoundException("Account you try to update not found");
        }
        return commandGateway.send( new UpdateAccountCommand(
                request.id(),
                request.balance(),
                new Date()
        ));
    }


    @GetMapping(path = "/eventStore/{accountId}")
    public Stream eventStream(@PathVariable(name = "accountId") String accountId){
        return eventStore.readEvents(accountId).asStream();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
