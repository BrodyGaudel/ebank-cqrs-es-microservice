package com.brodygaudel.gestionclients.common.events;

public class CustomerDeletedEvent extends BaseEvent<String>{
    public CustomerDeletedEvent(String id) {
        super(id);
    }
}
