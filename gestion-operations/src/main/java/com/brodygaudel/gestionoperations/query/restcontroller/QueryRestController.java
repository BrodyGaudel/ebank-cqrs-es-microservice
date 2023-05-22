package com.brodygaudel.gestionoperations.query.restcontroller;

import com.brodygaudel.gestionoperations.common.dtos.OperationResponseDTO;
import com.brodygaudel.gestionoperations.query.entities.Operation;
import com.brodygaudel.gestionoperations.query.mapping.Mappers;
import com.brodygaudel.gestionoperations.query.queries.GetAllOperationQuery;
import com.brodygaudel.gestionoperations.query.queries.GetOperationByAccountIdQuery;
import com.brodygaudel.gestionoperations.query.queries.GetOperationByIdQuery;
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
    public List<OperationResponseDTO> operationList() {
        List<Operation> operations = queryGateway.query(new GetAllOperationQuery(), ResponseTypes.multipleInstancesOf(Operation.class)).join();
        return mappers.fromListOfOperations(operations);
    }


    @GetMapping("/get/{id}")
    public OperationResponseDTO getOperationById(@PathVariable String id) {
        Operation operation = queryGateway.query(new GetOperationByIdQuery(id), ResponseTypes.instanceOf(Operation.class)).join();
        return mappers.fromOperation(operation);
    }

    @GetMapping("/find/{accountId}")
    public List<OperationResponseDTO> getOperationByAccountId(@PathVariable String accountId) {
        List<Operation> operations = queryGateway.query(new GetOperationByAccountIdQuery(accountId), ResponseTypes.multipleInstancesOf(Operation.class)).join();
        return mappers.fromListOfOperations(operations);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
