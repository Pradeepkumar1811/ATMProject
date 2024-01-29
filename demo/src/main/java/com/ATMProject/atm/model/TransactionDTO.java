package com.ATMProject.atm.model;

import java.time.LocalDateTime;

import com.ATMProject.atm.entities.Account;

public class TransactionDTO {
	 private double amount;
	 private LocalDateTime timestamp;
	 private Account account;
	public TransactionDTO() {
		super();
		// TODO Auto-generated constructor stub
	 }
	 public TransactionDTO(Account account,double amount, LocalDateTime timestamp) {
		super();
		this.account=account;
		this.amount = amount;
		this.timestamp = timestamp;
	 }
	 
	 
	public TransactionDTO(double amount, Account account) {
		super();
		this.amount = amount;
		this.account = account;
	}
	//getters and setters
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
