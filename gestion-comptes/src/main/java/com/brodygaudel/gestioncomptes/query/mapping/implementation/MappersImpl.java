package com.brodygaudel.gestioncomptes.query.mapping.implementation;

import com.brodygaudel.gestioncomptes.common.dtos.AccountResponseDTO;
import com.brodygaudel.gestioncomptes.query.entities.Account;
import com.brodygaudel.gestioncomptes.query.mapping.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public AccountResponseDTO fromAccount(Account account) {
        try{
            return new AccountResponseDTO(
                    account.getId(),
                    account.getStatus(),
                    account.getCurrency(),
                    account.getBalance(),
                    account.getCustomerId(),
                    account.getCreation(),
                    account.getLastUpdate()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AccountResponseDTO> fromListOfAccounts(List<Account> accounts) {
        try{
            return accounts.stream().map(this::fromAccount).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
