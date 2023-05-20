package com.brodygaudel.gestionclients.query.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String name;
    private String placeOfBirth;
    private String dateOfBirth;
    private String nationality;
    private String cin;
}
