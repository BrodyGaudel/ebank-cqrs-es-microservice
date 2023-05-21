package com.brodygaudel.gestioncomptes.common.events;

import com.brodygaudel.gestioncomptes.common.enums.AccountStatus;
import lombok.Getter;

public class AccountActivatedEvent extends BaseEvent<String>{

    @Getter
    private AccountStatus status;

    public AccountActivatedEvent(String id, AccountStatus status) {
        super(id);
        this.status = status;
    }


}
