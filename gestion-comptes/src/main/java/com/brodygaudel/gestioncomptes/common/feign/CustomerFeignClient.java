package com.brodygaudel.gestioncomptes.common.feign;

import com.brodygaudel.gestioncomptes.common.dtos.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER")
public interface CustomerFeignClient {
    @GetMapping("/customer/api/v3/queries/get/{id}")
    CustomerDTO getCustomerById(@PathVariable String id);
}
