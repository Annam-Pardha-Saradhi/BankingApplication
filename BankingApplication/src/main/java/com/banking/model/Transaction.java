package com.banking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type; // Deposit or Withdrawal
    private Double amount;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {}

    public Transaction(String type, Double amount, Account account) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.account = account;
    }

    // Getters and Setters
}
