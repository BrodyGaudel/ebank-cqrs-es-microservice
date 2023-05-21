package com.brodygaudel.gestioncomptes.common.events;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

public class AccountUpdatedEvent extends BaseEvent<String>{

    @Getter
    private BigDecimal balance;
    @Getter
    private Date date;

    public AccountUpdatedEvent(String id, BigDecimal balance, Date date) {
        super(id);
        this.balance = balance;
        this.date = date;
    }
}
