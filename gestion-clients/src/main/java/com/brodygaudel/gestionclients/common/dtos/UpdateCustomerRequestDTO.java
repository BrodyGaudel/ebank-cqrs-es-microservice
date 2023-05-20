package com.brodygaudel.gestionclients.common.dtos;

public record UpdateCustomerRequestDTO(String id,
                                       String firstname,
                                       String name,
                                       String placeOfBirth,
                                       String dateOfBirth,
                                       String nationality,
                                       String cin) {
}
