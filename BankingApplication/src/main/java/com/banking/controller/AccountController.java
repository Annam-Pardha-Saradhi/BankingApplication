package com.banking.controller;

import com.banking.model.Account;
import com.banking.model.Transaction;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(@RequestParam String name, @RequestParam Double initialDeposit) {
        return accountService.createAccount(name, initialDeposit);
    }

    @PostMapping("/{accountId}/deposit")
    public Account deposit(@PathVariable Long accountId, @RequestParam Double amount) {
        return accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw")
    public Account withdraw(@PathVariable Long accountId, @RequestParam Double amount) {
        return accountService.withdraw(accountId, amount);
    }

    @GetMapping("/{accountId}/balance")
    public Double getBalance(@PathVariable Long accountId) {
        return accountService.getBalance(accountId);
    }

    @GetMapping("/{accountId}/transactions")
    public List<Transaction> getTransactionHistory(@PathVariable Long accountId) {
        return accountService.getTransactionHistory(accountId);
    }
}
