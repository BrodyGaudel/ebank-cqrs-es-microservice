package com.brodygaudel.gestionclients.common.events;

import lombok.Getter;


public class CustomerUpdatedEvent extends BaseEvent<String>{

    @Getter
    private String firstname;

    @Getter
    private String name;

    @Getter
    private String placeOfBirth;

    @Getter
    private String dateOfBirth;

    @Getter
    private String nationality;

    @Getter
    private String cin;


    public CustomerUpdatedEvent(String id, String firstname, String name, String placeOfBirth, String dateOfBirth, String nationality, String cin) {
        super(id);
        this.firstname = firstname;
        this.name = name;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.cin = cin;
    }
}
