package org.condueetpension.data.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.condueetpension.userMannagementUtility.constants.Currency;
import org.condueetpension.userMannagementUtility.constants.TransactionStatus;
import org.condueetpension.userMannagementUtility.constants.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private BigDecimal transactionAmount;
    private Timestamp dateInitiated;
    private String sourceAccount;
    private String destinationAccount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String transactionDate;
    private String userId;
    private String transactionRef;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Object meta;
}
