package com.brodygaudel.gestioncomptes.common.commands;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

public class UpdateAccountCommand extends BaseCommand<String>{

    @Getter
    private BigDecimal amount;
    @Getter
    private Date date;
    public UpdateAccountCommand(String id, BigDecimal amount, Date date) {
        super(id);
        this.amount = amount;
        this.date = date;
    }
}
