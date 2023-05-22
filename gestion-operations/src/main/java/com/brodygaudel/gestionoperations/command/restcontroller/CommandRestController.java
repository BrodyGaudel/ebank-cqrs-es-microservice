package com.brodygaudel.gestionoperations.command.restcontroller;

import com.brodygaudel.gestionoperations.common.commands.CreateOperationCommand;
import com.brodygaudel.gestionoperations.common.dtos.AccountResponseDTO;
import com.brodygaudel.gestionoperations.common.dtos.OperationRequestDTO;
import com.brodygaudel.gestionoperations.common.enums.AccountStatus;
import com.brodygaudel.gestionoperations.common.enums.OperationType;
import com.brodygaudel.gestionoperations.common.exceptions.BalanceNotSufficientException;
import com.brodygaudel.gestionoperations.common.exceptions.OperationRejectedException;
import com.brodygaudel.gestionoperations.common.feign.AccountFeignClient;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v3/commands")
public class CommandRestController {

    private final CommandGateway commandGateway;
    private final EventStore eventStore;
    private final AccountFeignClient accountFeignClient;

    public CommandRestController(CommandGateway commandGateway, EventStore eventStore, AccountFeignClient accountFeignClient) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
        this.accountFeignClient = accountFeignClient;
    }


    @PostMapping(path = "/create")
    public CompletableFuture<String> createCustomer(@RequestBody OperationRequestDTO request) throws OperationRejectedException, BalanceNotSufficientException {
        AccountResponseDTO account = accountFeignClient.getAccountById(request.accountId());
        if(account == null){
            throw new OperationRejectedException("account not found");
        }
        if(account.status().equals(AccountStatus.SUSPENDED)){
            throw new OperationRejectedException("Account is Suspended");
        }
        if(request.type().equals(OperationType.DEBIT) && (request.amount().compareTo(account.balance()) > 0)){
                throw new BalanceNotSufficientException("Balance not sufficient");
        }
        if(request.amount().compareTo(new BigDecimal("0")) <0){
            throw new BalanceNotSufficientException("Balance not sufficient");
        }
        return commandGateway.send( new CreateOperationCommand(
                UUID.randomUUID().toString(),
                request.type(),
                request.currency(),
                request.amount(),
                request.description(),
                request.accountId(),
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
