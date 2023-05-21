package com.brodygaudel.gestioncomptes.command.aggregates;

import com.brodygaudel.gestioncomptes.common.commands.CreateAccountCommand;
import com.brodygaudel.gestioncomptes.common.commands.UpdateAccountCommand;
import com.brodygaudel.gestioncomptes.common.enums.AccountStatus;
import com.brodygaudel.gestioncomptes.common.enums.Currency;

import com.brodygaudel.gestioncomptes.common.events.AccountActivatedEvent;
import com.brodygaudel.gestioncomptes.common.events.AccountCreatedEvent;
import com.brodygaudel.gestioncomptes.common.events.AccountUpdatedEvent;
import com.brodygaudel.gestioncomptes.common.exceptions.AccountSuspendedException;
import com.brodygaudel.gestioncomptes.common.exceptions.BalanceNotSufficientException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.Date;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String id;
    private AccountStatus status;
    private Currency currency;
    private BigDecimal balance;
    private String customerId;
    private Date creation;

    public AccountAggregate() {
        //required by Axon framework
    }

    //CREATE ACCOUNT AND ACTIVATE IT
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) throws BalanceNotSufficientException {
        if(command.getBalance().compareTo(new BigDecimal("0")) < 0){
            throw new BalanceNotSufficientException("balance must not be negative");
        }
        AggregateLifecycle.apply( new AccountCreatedEvent(
                command.getId(),
                command.getBalance(),
                command.getCurrency(),
                AccountStatus.CREATED, new Date(), command.getCustomerId()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.id = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = AccountStatus.CREATED;
        this.creation = event.getCreation();
        this.customerId = event.getCustomerId();
        AggregateLifecycle.apply( new AccountActivatedEvent(
                event.getId(), AccountStatus.ACTIVATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.status = event.getStatus();
    }

    //ACCOUNT UPDATE
    @CommandHandler
    public void handle(UpdateAccountCommand command) throws BalanceNotSufficientException, AccountSuspendedException {
        if(command.getAmount().compareTo(new BigDecimal("0")) < 0){
            throw new BalanceNotSufficientException("amount must not be negative");
        }
        if(this.status.equals(AccountStatus.SUSPENDED)){
            throw new AccountSuspendedException("account suspended");
        }
        AggregateLifecycle.apply(new AccountUpdatedEvent(
                command.getId(), command.getAmount(), command.getDate()
        ));
    }

    @EventSourcingHandler
    public void on(AccountUpdatedEvent event){
        this.balance = event.getBalance();
    }

}
