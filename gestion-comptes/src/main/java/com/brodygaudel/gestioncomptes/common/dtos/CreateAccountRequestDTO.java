package com.brodygaudel.gestioncomptes.common.dtos;

import com.brodygaudel.gestioncomptes.common.enums.Currency;

import java.math.BigDecimal;

public record CreateAccountRequestDTO(BigDecimal initialBalance, Currency currency, String customerId) {
}
