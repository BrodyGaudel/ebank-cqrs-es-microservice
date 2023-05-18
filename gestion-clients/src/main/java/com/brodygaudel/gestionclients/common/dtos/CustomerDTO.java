package com.brodygaudel.gestionclients.common.dtos;

import java.util.Date;

public record CustomerDTO(String id,
                          String firstname,
                          String name,
                          String placeOfBirth,
                          String dateOfBirth,
                          String nationality,
                          String cin,
                          Date creation,
                          Date lastUpdate) {
}
