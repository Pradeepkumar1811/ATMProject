package com.ATMProject.atm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ATMProject.atm.model.AccountDTO;
import com.ATMProject.atm.model.TransactionDTO;
import com.ATMProject.atm.service.ATMService;

@RestController
@RequestMapping("/api/atm")
public class Controller {
    @Autowired
    private ATMService atmService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody AccountDTO accountDTO) {
        String result = atmService.createUser(accountDTO.getUsername(), accountDTO.getPin());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/balance")
    public String checkBalance(@RequestBody AccountDTO accountDTO) {
        String message = atmService.checkBalance(accountDTO.getAccountNumber(), accountDTO.getPin());
        return message;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody AccountDTO accountDTO, @RequestParam double amount) {
        String result = atmService.deposit(accountDTO.getAccountNumber(), amount);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody AccountDTO accountDTO, @RequestParam double amount) {
        String result = atmService.withdraw(accountDTO.getAccountNumber(), accountDTO.getPin(), amount);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@RequestBody AccountDTO accountDTO) {
        List<TransactionDTO> transactions = atmService.getTransactions(accountDTO.getAccountNumber());
        return ResponseEntity.ok(transactions);
    }
}



