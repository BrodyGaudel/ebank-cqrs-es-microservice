package com.brodygaudel.gestionclients.query.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String name;
    private String placeOfBirth;
    private String dateOfBirth;
    private String nationality;
    private String cin;
    private Date creation;
    private Date lastUpdate;
}
