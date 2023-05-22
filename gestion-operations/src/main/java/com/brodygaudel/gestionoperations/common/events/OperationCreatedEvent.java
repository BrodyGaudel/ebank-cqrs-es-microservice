package com.brodygaudel.gestionoperations.common.events;

import com.brodygaudel.gestionoperations.common.enums.Currency;
import com.brodygaudel.gestionoperations.common.enums.OperationType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

public class OperationCreatedEvent extends BaseEvent<String> {

    @Getter
    private Date date;

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

    public OperationCreatedEvent(String id, Date date, OperationType type, Currency currency, BigDecimal amount, String description, String accountId) {
        super(id);
        this.date = date;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
    }
}
