package com.brodygaudel.gestioncomptes.query.services.implementation;

import com.brodygaudel.gestioncomptes.common.exceptions.AccountNotFoundException;
import com.brodygaudel.gestioncomptes.query.entities.Account;
import com.brodygaudel.gestioncomptes.query.repositories.AccountRepository;
import com.brodygaudel.gestioncomptes.query.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account saveAccount(Account account) {
        log.info("In saveAccount()");
        try{
            Account a = accountRepository.save(account);
            log.info("account saved");
            return a;
        }catch (Exception e){
            log.error("account not saved : "+e.getMessage());
            return null;
        }
    }

    @Override
    public Account updateAccount(Account account) {
        log.info("In updateAccount()");
        try{
            Account a = accountRepository.findById(account.getId()).orElse(null);
            if(a == null){
                log.error("account with id :"+account.getId()+" : not found");
                return null;
            }else{
                a.setBalance(account.getBalance());
                a.setCurrency(account.getCurrency());
                Account updated = accountRepository.save(a);
                log.info("account updated");
                return updated;
            }
        }catch (Exception e){
            log.error("account not updated");
            return null;
        }
    }

    @Override
    public Account findAccountById(String id) throws AccountNotFoundException {
        log.info("In findAccountById()");
        Account account = accountRepository.findById(id)
                .orElseThrow( () -> new AccountNotFoundException("Account with id :"+id+" not found"));
        log.info("account found");
        return account;
    }

    @Override
    public Account findAccountByCustomerId(String customerId) throws AccountNotFoundException {
        log.info("In findAccountByCustomerId()");
        Account account = accountRepository.findByCustomerId(customerId);
        if(account == null){
            throw new AccountNotFoundException("account with customerId :"+customerId+" not found");
        }else{
           log.info("account with customerId :"+customerId+" found");
           return account;
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        try {
            return accountRepository.findAll();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteAccountById(String id) {
        log.info("In deleteAccountById()");
        try{
            accountRepository.deleteById(id);
            log.info("account deleted");
        }catch (Exception e){
            log.error("account not deleted");
        }
    }
}
