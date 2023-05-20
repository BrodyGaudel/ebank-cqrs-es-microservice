package com.brodygaudel.gestionclients.command.aggregate;

import com.brodygaudel.gestionclients.common.commands.CreateCustomerCommand;
import com.brodygaudel.gestionclients.common.commands.DeleteCustomerCommand;
import com.brodygaudel.gestionclients.common.commands.UpdateCustomerCommand;
import com.brodygaudel.gestionclients.common.events.CustomerCreatedEvent;
import com.brodygaudel.gestionclients.common.events.CustomerDeletedEvent;
import com.brodygaudel.gestionclients.common.events.CustomerUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


@Aggregate
public class CustomerAggregate {

    @AggregateIdentifier
    private String id;
    private String firstname;
    private String name;
    private String placeOfBirth;
    private String dateOfBirth;
    private String nationality;
    private String cin;


    public CustomerAggregate() {
        //required by Axon
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        AggregateLifecycle.apply( new CustomerCreatedEvent(
                command.getId(),
                command.getFirstname(),
                command.getName(),
                command.getPlaceOfBirth(),
                command.getDateOfBirth(),
                command.getNationality(),
                command.getCin()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        this.id = event.getId();
        this.firstname = event.getFirstname();
        this.name = event.getName();
        this.placeOfBirth = event.getPlaceOfBirth();
        this.dateOfBirth = event.getDateOfBirth();
        this.nationality = event.getNationality();
    }

    //update

    @CommandHandler
    public void handle(UpdateCustomerCommand command){
        AggregateLifecycle.apply( new CustomerUpdatedEvent(
                command.getId(),
                command.getFirstname(),
                command.getName(),
                command.getPlaceOfBirth(),
                command.getDateOfBirth(),
                command.getNationality(),
                command.getCin()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event){
        this.id = event.getId();
        this.firstname = event.getFirstname();
        this.name = event.getName();
        this.placeOfBirth = event.getPlaceOfBirth();
        this.dateOfBirth = event.getDateOfBirth();
        this.nationality = event.getNationality();
        this.cin = event.getCin();
    }

    // delete
    @CommandHandler
    public void handle(DeleteCustomerCommand command){
        AggregateLifecycle.apply( new CustomerDeletedEvent(
                command.getId()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerDeletedEvent event){
        this.id = event.getId();
    }



}
