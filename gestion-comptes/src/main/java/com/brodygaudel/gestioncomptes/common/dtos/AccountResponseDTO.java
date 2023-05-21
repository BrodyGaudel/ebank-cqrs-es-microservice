package com.brodygaudel.gestioncomptes.common.dtos;

import com.brodygaudel.gestioncomptes.common.enums.AccountStatus;
import com.brodygaudel.gestioncomptes.common.enums.Currency;

import java.math.BigDecimal;
import java.util.Date;

public record AccountResponseDTO(
        String id,
        AccountStatus status,
        Currency currency,
        BigDecimal balance,
        String customerId,
        Date creation,
        Date lastUpdate
        ) {
}
