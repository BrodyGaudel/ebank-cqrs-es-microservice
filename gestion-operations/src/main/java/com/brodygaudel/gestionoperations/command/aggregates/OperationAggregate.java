package com.brodygaudel.gestionoperations.command.aggregates;

import com.brodygaudel.gestionoperations.common.commands.CreateOperationCommand;
import com.brodygaudel.gestionoperations.common.enums.Currency;
import com.brodygaudel.gestionoperations.common.enums.OperationType;
import com.brodygaudel.gestionoperations.common.events.OperationCreatedEvent;
import com.brodygaudel.gestionoperations.common.exceptions.BalanceNotSufficientException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;

@Aggregate
public class OperationAggregate {

    @AggregateIdentifier
    private String id;
    private Date date;
    private OperationType type;
    private Currency currency;
    private BigDecimal amount;
    private String description;
    private String accountId;

    public OperationAggregate() {
        //required by Axon
    }

    @CommandHandler
    public OperationAggregate(CreateOperationCommand command) throws BalanceNotSufficientException {
        if(command.getAmount().compareTo(new BigDecimal("0")) < 0){
            throw new BalanceNotSufficientException("balance must not be negative");
        }
        AggregateLifecycle.apply( new OperationCreatedEvent(
                command.getId(),
                command.getDate(),
                command.getType(),
                command.getCurrency(),
                command.getAmount(),
                command.getDescription(),
                command.getAccountId()
        ));
    }

    @EventSourcingHandler
    public void on(OperationCreatedEvent event){
        this.id = event.getId();
        this.accountId = event.getAccountId();
        this.currency = event.getCurrency();
        this.date = event.getDate();
        this.type = event.getType();
        this.description = event.getDescription();
        this.amount = event.getAmount();
    }


}
