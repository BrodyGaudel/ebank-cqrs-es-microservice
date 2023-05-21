package com.brodygaudel.gestioncomptes.common.commands;

import com.brodygaudel.gestioncomptes.common.enums.Currency;
import lombok.Getter;

import java.math.BigDecimal;

public class CreateAccountCommand  extends BaseCommand<String>{

    @Getter
    private Currency currency;

    @Getter
    private BigDecimal balance;

    @Getter
    private String customerId;

    public CreateAccountCommand(String id, Currency currency, BigDecimal balance, String customerId) {
        super(id);
        this.currency = currency;
        this.balance = balance;
        this.customerId = customerId;
    }
}
