package com.brodygaudel.gestionoperations.query.entities;

import com.brodygaudel.gestionoperations.common.enums.Currency;
import com.brodygaudel.gestionoperations.common.enums.OperationType;
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
public class Operation {
    @Id
    private String id;
    private Date date;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal amount;

    private String description;

    @Column(nullable = false)
    private String accountId;
}
