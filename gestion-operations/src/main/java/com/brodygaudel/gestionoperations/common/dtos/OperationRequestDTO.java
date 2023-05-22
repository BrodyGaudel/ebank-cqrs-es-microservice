package com.brodygaudel.gestionoperations.common.dtos;

import com.brodygaudel.gestionoperations.common.enums.Currency;
import com.brodygaudel.gestionoperations.common.enums.OperationType;

import java.math.BigDecimal;

public record OperationRequestDTO(
        OperationType type,
        Currency currency,
        BigDecimal amount,
        String description,
        String accountId) {
}
