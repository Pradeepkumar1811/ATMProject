package com.ATMProject.atm.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ATMProject.atm.converter.DTOConverter;
import com.ATMProject.atm.entities.*;
import com.ATMProject.atm.exception.ATMException;
import com.ATMProject.atm.model.*;
import com.ATMProject.atm.repository.*;
import com.ATMProject.atm.service.ATMService;


@Service
public class ATMServiceIMPL implements ATMService{
	 @Autowired(required=true)
	 AccountRepository accountRepository;

	 @Autowired(required=true)
	 TransactionRepository transactionRepository;
	 
	 @Override
	 public String checkBalance(String accountNumber,String pin) {
	    Account accountEntity = validateAccount(accountNumber,pin);
	    if (accountEntity != null) {
	        return "Your balance is "+accountEntity.getBalance();
	    } else {
	        throw new ATMException("Invalid account number.");
	    }
	}
	 @Override
	 public String withdraw(String accountNumber, String pin, double amount) {
	     Account account = validateAccount(accountNumber, pin);
	     if (account.getBalance() >= amount) {
	         account.setBalance(account.getBalance() - amount);
	         accountRepository.save(account);

	         recordTransaction(account, amount,"WITHDRAWAL");

	         return "Withdrawal successful. Remaining balance: " + account.getBalance();
	     } else {
	         throw new ATMException("Insufficient funds for withdrawal.");
	     }
	 }
	 @Override
	 public String deposit(String accountNumber, double amount) {
	     Account account = accountRepository.findByAccountNumber(accountNumber);
	     if (account != null) {
	         account.setBalance(account.getBalance() + amount);
	         accountRepository.save(account);

	         recordTransaction(account, amount, "DEPOSIT");

	         return "Deposit successful. New balance: " + account.getBalance();
	     } else {
	         throw new ATMException("Invalid account number to deposit.");
	     }
	 }
	 private void recordTransaction(Account accountEntity, double amount, String type) {
	    if (accountEntity != null) {
	        Transaction transactionEntity = new Transaction();
	        transactionEntity.setAccount(accountEntity);
	        transactionEntity.setAmount(type.equals("DEPOSIT") ? amount : -amount);
	        transactionEntity.setTimestamp(LocalDateTime.now());

	        // Save the transaction DTO to the repository (if needed)
	        TransactionDTO transactionDTO = DTOConverter.convertTransactionEntityToDTO(transactionEntity);
	        if (transactionEntity != null) {
	            transactionRepository.save(transactionEntity);
	        }
	    } else {
	        // Handle the case where accountEntity is null
	        // This might be an error condition, so you could log a message or throw an exception
	        throw new ATMException("Cannot record transaction for null accountEntity");
	    }
	}
	 private Account validateAccount(String accountNumber, String pin) {
	     Account account = accountRepository.findByAccountNumberAndPin(accountNumber, pin);
	     if (account == null) {
	         throw new ATMException("Invalid account number or PIN.");
	     }
	     return account;
	 }
	 @Override
	 public String createUser(String username, String pin) {
	     // Check if the username is empty
	     if (username.isEmpty()) {
	         throw new ATMException("Username Should not be empty");
	     }

	     // Create a new account
	     Account newAccount = new Account();
	     newAccount.setAccountNumber(generateUniqueAccountNumber());
	     newAccount.setUsername(username);
	     newAccount.setPin(pin);
	     newAccount.setBalance(0.0);
	     AccountDTO accountDTO=DTOConverter.convertAccountEntityToDTO(newAccount);

	     // Save the new account
	     accountRepository.save(newAccount);

	     return "User created successfully. Account number: " + newAccount.getAccountNumber();
	 }
	 private String generateUniqueAccountNumber() {
		 Random random=new Random();
		 String accno="";
		 for(int i=0;i<13;i++) {
			 accno+=random.nextInt(9);
		 }
	     return accno;
	 }
	 @Override
	 public List<TransactionDTO> getTransactions(String accountNumber) {
	     List<Transaction> transactions = transactionRepository.findByAccount_AccountNumberOrderByTimestampDesc(accountNumber);
	     return transactions.stream()
	             .map(transaction -> new TransactionDTO(
	                     transaction.getAccount(),
	                     transaction.getAmount(),
	                     transaction.getTimestamp()
	             ))
	             .collect(Collectors.toList());
	 }
}
