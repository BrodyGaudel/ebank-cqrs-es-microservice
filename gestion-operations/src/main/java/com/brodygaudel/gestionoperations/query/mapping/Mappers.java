package com.brodygaudel.gestionoperations.query.mapping;

import com.brodygaudel.gestionoperations.common.dtos.OperationResponseDTO;
import com.brodygaudel.gestionoperations.query.entities.Operation;

import java.util.List;

public interface Mappers {
    OperationResponseDTO fromOperation(Operation operation);
    List<OperationResponseDTO> fromListOfOperations(List<Operation> operations);
}
