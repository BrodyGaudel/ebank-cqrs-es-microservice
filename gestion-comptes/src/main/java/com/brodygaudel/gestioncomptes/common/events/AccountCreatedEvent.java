package com.brodygaudel.gestioncomptes.common.events;

import com.brodygaudel.gestioncomptes.common.enums.AccountStatus;
import com.brodygaudel.gestioncomptes.common.enums.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

public class AccountCreatedEvent extends BaseEvent<String>{
    @Getter
    private BigDecimal initialBalance;
    @Getter
    private Currency currency;
    @Getter
    private AccountStatus status;

    @Getter
    private Date creation;

    @Getter
    private String customerId;

    public AccountCreatedEvent(String id, BigDecimal initialBalance, Currency currency, AccountStatus status, Date creation, String customerId) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.status = status;
        this.creation = creation;
        this.customerId = customerId;
    }
}
