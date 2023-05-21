package com.brodygaudel.gestioncomptes.query.services;

import com.brodygaudel.gestioncomptes.common.exceptions.AccountNotFoundException;
import com.brodygaudel.gestioncomptes.query.entities.Account;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    Account updateAccount(Account account);
    Account findAccountById(String id) throws AccountNotFoundException;
    Account findAccountByCustomerId(String customerId) throws AccountNotFoundException;
    List<Account> findAllAccounts();
    void deleteAccountById(String id);
}
