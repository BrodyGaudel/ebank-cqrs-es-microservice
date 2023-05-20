package com.brodygaudel.gestionclients.common.commands;

import lombok.Getter;

public class CreateCustomerCommand extends BaseCommand<String>{

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

    public CreateCustomerCommand(String id, String firstname, String name, String placeOfBirth, String dateOfBirth, String nationality, String cin) {
        super(id);
        this.firstname = firstname;
        this.name = name;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.cin = cin;
    }
}
