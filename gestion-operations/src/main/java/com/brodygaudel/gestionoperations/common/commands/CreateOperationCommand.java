package com.brodygaudel.gestionoperations.common.commands;

import com.brodygaudel.gestionoperations.common.enums.Currency;
import com.brodygaudel.gestionoperations.common.enums.OperationType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

public class CreateOperationCommand extends BaseCommand<String>{

    @Getter
    private OperationType type;
    @Getter
    private Currency currency;
    @Getter
    private BigDecimal amount;
    @Getter
    private String description;
    @Getter
    private String accountId;
    @Getter
    private Date date;

    public CreateOperationCommand(String id, OperationType type, Currency currency, BigDecimal amount, String description, String accountId, Date date) {
        super(id);
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
        this.date = date;
    }
}
