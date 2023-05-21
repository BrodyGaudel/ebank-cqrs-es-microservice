package com.brodygaudel.gestioncomptes.query.mapping;

import com.brodygaudel.gestioncomptes.common.dtos.AccountResponseDTO;
import com.brodygaudel.gestioncomptes.query.entities.Account;

import java.util.List;

public interface Mappers {
    AccountResponseDTO fromAccount(Account account);
    List<AccountResponseDTO> fromListOfAccounts(List<Account> accounts);
}
