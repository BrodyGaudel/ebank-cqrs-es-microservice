package com.brodygaudel.gestioncomptes.query.services;

import com.brodygaudel.gestioncomptes.common.events.AccountActivatedEvent;
import com.brodygaudel.gestioncomptes.common.events.AccountCreatedEvent;
import com.brodygaudel.gestioncomptes.common.events.AccountUpdatedEvent;
import com.brodygaudel.gestioncomptes.query.entities.Account;
import com.brodygaudel.gestioncomptes.query.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ServiceCommandHandler {

    private final AccountRepository accountRepository;

    public ServiceCommandHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("AccountCreatedEvent received");
        Account account = new Account();
        account.setBalance(event.getInitialBalance());
        account.setStatus(event.getStatus());
        account.setId(event.getId());
        account.setCustomerId(event.getCustomerId());
        account.setCurrency(event.getCurrency());
        account.setCreation(event.getCreation());
        account.setLastUpdate(null);
        accountRepository.save(account);
        log.info("account saved");
    }

    @EventHandler
    public void  on(AccountActivatedEvent event) {
        log.info("AccountActivatedEvent received");
        Account account = accountRepository.findById(event.getId()).orElse(null);
        if(account == null){
            log.warn("account is null");
        }else{
            account.setStatus(event.getStatus());
            account.setLastUpdate(new Date());
            accountRepository.save(account);
            log.info("account activated");
        }
    }

    @EventHandler
    public void on(AccountUpdatedEvent event){
        log.info("AccountUpdatedEvent received");
        Account account = accountRepository.findById(event.getId()).orElse(null);
        if(account == null){
            log.warn("account is null");
        }else{
            account.setBalance(event.getBalance());
            account.setLastUpdate(event.getDate());
            accountRepository.save(account);
            log.info("account updated");
        }
    }

}
