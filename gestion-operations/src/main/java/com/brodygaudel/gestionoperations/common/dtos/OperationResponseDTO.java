package com.brodygaudel.gestionoperations.common.dtos;

import com.brodygaudel.gestionoperations.common.enums.Currency;
import com.brodygaudel.gestionoperations.common.enums.OperationType;

import java.math.BigDecimal;
import java.util.Date;

public record OperationResponseDTO(
        String id,
        Date date,
        OperationType type,
        Currency currency,
        BigDecimal amount,
        String description,
        String accountId) { }
