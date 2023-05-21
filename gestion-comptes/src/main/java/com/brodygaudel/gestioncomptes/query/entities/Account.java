package com.brodygaudel.gestioncomptes.query.entities;

import com.brodygaudel.gestioncomptes.common.enums.AccountStatus;
import com.brodygaudel.gestioncomptes.common.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal balance;
    @Column(unique = true)
    private String customerId;
    private Date creation;
}
