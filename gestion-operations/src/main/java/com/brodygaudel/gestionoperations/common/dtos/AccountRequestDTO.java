package com.brodygaudel.gestionoperations.common.dtos;

import java.math.BigDecimal;

public record AccountRequestDTO(String id, BigDecimal balance) {
}
