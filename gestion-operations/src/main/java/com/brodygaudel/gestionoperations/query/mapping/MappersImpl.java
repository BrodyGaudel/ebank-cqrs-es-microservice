package com.brodygaudel.gestionoperations.query.mapping;

import com.brodygaudel.gestionoperations.common.dtos.OperationResponseDTO;
import com.brodygaudel.gestionoperations.query.entities.Operation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers{
    @Override
    public OperationResponseDTO fromOperation(Operation operation) {
        try{
            return new OperationResponseDTO(
                    operation.getId(),
                    operation.getDate(),
                    operation.getType(),
                    operation.getCurrency(),
                    operation.getAmount(),
                    operation.getDescription(),
                    operation.getAccountId()
            );
        }catch (Exception e){
           return null;
        }
    }

    @Override
    public List<OperationResponseDTO> fromListOfOperations(List<Operation> operations) {
        try{
            return operations.stream().map(this::fromOperation).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
