package com.brodygaudel.gestionclients.common.dtos;

public record CreateCustomerRequestDTO(
        String firstname,
        String name,
        String placeOfBirth,
        String dateOfBirth,
        String nationality,
        String cin) {
}
