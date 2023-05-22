package com.brodygaudel.gestionoperations.query.services;

import com.brodygaudel.gestionoperations.common.dtos.AccountRequestDTO;
import com.brodygaudel.gestionoperations.common.dtos.AccountResponseDTO;
import com.brodygaudel.gestionoperations.common.enums.OperationType;
import com.brodygaudel.gestionoperations.common.events.OperationCreatedEvent;
import com.brodygaudel.gestionoperations.common.feign.AccountFeignClient;
import com.brodygaudel.gestionoperations.query.entities.Operation;
import com.brodygaudel.gestionoperations.query.queries.GetAllOperationQuery;
import com.brodygaudel.gestionoperations.query.queries.GetOperationByAccountIdQuery;
import com.brodygaudel.gestionoperations.query.queries.GetOperationByIdQuery;
import com.brodygaudel.gestionoperations.query.repositories.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class OperationServiceHandler {
    private final OperationRepository operationRepository;
    private final AccountFeignClient accountFeignClient;

    public OperationServiceHandler(OperationRepository operationRepository, AccountFeignClient accountFeignClient) {
        this.operationRepository = operationRepository;
        this.accountFeignClient = accountFeignClient;
    }

    @EventHandler
    public void on(OperationCreatedEvent event){
        log.info("OperationCreatedEvent received");
        Operation operation = Operation.builder()
                .id(event.getId())
                .accountId(event.getAccountId())
                .amount(event.getAmount())
                .description(event.getDescription())
                .currency(event.getCurrency())
                .date(event.getDate())
                .type(event.getType())
                .build();
        operationRepository.save(operation);
        log.info("operation saved");
        AccountResponseDTO account = accountFeignClient.getAccountById(event.getAccountId());
        BigDecimal balance = account.balance();
        if(operation.getType().equals(OperationType.DEBIT)){
            balance = balance.subtract(event.getAmount());
            AccountRequestDTO requestDTO = new AccountRequestDTO(account.id(), balance);
            accountFeignClient.updateAccount(requestDTO);
            log.info("debit posted");
        }else {
            balance = event.getAmount().add(balance);
            AccountRequestDTO requestDTO = new AccountRequestDTO(account.id(), balance);
            log.info("credit posted");
            accountFeignClient.updateAccount(requestDTO);
        }

    }

    @QueryHandler
    public List<Operation> on(GetAllOperationQuery query) {
        return operationRepository.findAll();
    }

    @QueryHandler
    public Operation on(GetOperationByIdQuery query)  {
        return operationRepository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public List<Operation> on(GetOperationByAccountIdQuery query)  {
        return operationRepository.findByAccountId(query.getId());
    }

}
