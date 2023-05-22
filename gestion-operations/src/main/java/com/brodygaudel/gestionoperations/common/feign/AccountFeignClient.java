package com.brodygaudel.gestionoperations.common.feign;

import com.brodygaudel.gestionoperations.common.dtos.AccountRequestDTO;
import com.brodygaudel.gestionoperations.common.dtos.AccountResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ACCOUNT")
public interface AccountFeignClient {
    @GetMapping("/account/api/v3/queries/get/{id}")
    AccountResponseDTO getAccountById(@PathVariable String id);

    @PutMapping("/account/api/v3/commands/update")
    AccountResponseDTO updateAccount(@RequestBody AccountRequestDTO accountRequestDTO);
}
