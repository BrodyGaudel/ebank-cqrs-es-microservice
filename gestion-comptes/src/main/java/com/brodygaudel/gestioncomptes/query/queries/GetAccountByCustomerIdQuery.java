package com.brodygaudel.gestioncomptes.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAccountByCustomerIdQuery {
    private String customerId;
}
