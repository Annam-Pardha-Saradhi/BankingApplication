package com.banking.service;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(String name, Double initialDeposit) {
        Account account = new Account(name, initialDeposit);
        accountRepository.save(account);
        return account;
    }

    public Account deposit(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction("Deposit", amount, account);
        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, Double amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction("Withdrawal", amount, account);
        transactionRepository.save(transaction);

        return accountRepository.save(account);
    }

    public Double getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getTransactions();
    }
}
