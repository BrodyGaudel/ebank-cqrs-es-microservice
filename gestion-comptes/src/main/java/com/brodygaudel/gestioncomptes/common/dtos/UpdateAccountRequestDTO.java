package com.brodygaudel.gestioncomptes.common.dtos;


import java.math.BigDecimal;

public record UpdateAccountRequestDTO(String id, BigDecimal balance) {
}
