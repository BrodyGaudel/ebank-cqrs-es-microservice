package com.brodygaudel.gestionclients.command.restcontroller;

import com.brodygaudel.gestionclients.common.commands.CreateCustomerCommand;
import com.brodygaudel.gestionclients.common.commands.DeleteCustomerCommand;
import com.brodygaudel.gestionclients.common.commands.UpdateCustomerCommand;
import com.brodygaudel.gestionclients.common.dtos.CreateCustomerRequestDTO;
import com.brodygaudel.gestionclients.common.dtos.UpdateCustomerRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v3/commands")
public class CommandRestController {

    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    public CommandRestController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDTO request){
        return commandGateway.send( new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                request.firstname(),
                request.name(),
                request.placeOfBirth(),
                request.dateOfBirth(),
                request.nationality(),
                request.cin()
        ));
    }

    @PutMapping(path = "/update")
    public CompletableFuture<String> updateCustomer(@RequestBody UpdateCustomerRequestDTO request){
        return commandGateway.send( new UpdateCustomerCommand(
                request.id(),
                request.firstname(),
                request.name(),
                request.placeOfBirth(),
                request.dateOfBirth(),
                request.nationality(),
                request.cin()
        ));
    }

    @DeleteMapping(path = "/delete/{id}")
    public CompletableFuture<String> deleteCustomer(@PathVariable(name = "id") String id){
        return commandGateway.send( new DeleteCustomerCommand(id));
    }

    @GetMapping(path = "/eventStore/{id}")
    public Stream eventStream(@PathVariable(name = "id") String id){
        return eventStore.readEvents(id).asStream();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
