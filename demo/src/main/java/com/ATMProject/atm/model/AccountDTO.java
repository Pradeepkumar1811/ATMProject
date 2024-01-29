package com.ATMProject.atm.model;

import java.util.List;


public class AccountDTO {

	 private String accountNumber;
	 private String username;
	 private double balance;
	 private String pin;
	 private List<TransactionDTO> transactions;
	 public AccountDTO(String accountNumber, String username, double balance, String pin,
			List<TransactionDTO> transactions) {
		this.accountNumber = accountNumber;
		this.username = username;
		this.balance = balance;
		this.pin = pin;
		this.transactions = transactions;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	 public AccountDTO(String accountNumber, String username, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.username = username;
		this.balance = balance;
	}
	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 // getters and setters
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public List<TransactionDTO> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}

}

