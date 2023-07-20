package org.condueetpension.data.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TransactionAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionAuthId;
    private String transactionPin;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
}
