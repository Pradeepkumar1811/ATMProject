package com.ATMProject.atm.service;

import java.util.List;

//import com.ATMProject.atm.entities.Transaction;
import com.ATMProject.atm.model.*;
public interface ATMService {
	String createUser(String username, String pin);
	String checkBalance(String accountNumber,String pin);
	String withdraw(String accountNumber, String pin, double amount);
	String deposit(String accountNumber, double amount);
	List<TransactionDTO> getTransactions(String accountNumber);
}
