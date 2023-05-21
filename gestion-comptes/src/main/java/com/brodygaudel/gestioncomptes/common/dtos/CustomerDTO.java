package com.brodygaudel.gestioncomptes.common.dtos;

public record CustomerDTO(String id,
                          String firstname,
                          String name,
                          String placeOfBirth,
                          String dateOfBirth,
                          String nationality,
                          String cin) {
}
