package com.brodygaudel.gestionoperations.common.dtos;

import com.brodygaudel.gestionoperations.common.enums.AccountStatus;
import com.brodygaudel.gestionoperations.common.enums.Currency;

import java.math.BigDecimal;
import java.util.Date;

public record AccountResponseDTO(String id,
                                 AccountStatus status,
                                 Currency currency,
                                 BigDecimal balance,
                                 String customerId,
                                 Date creation,
                                 Date lastUpdate) {
}
