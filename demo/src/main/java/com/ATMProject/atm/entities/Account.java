package com.ATMProject.atm.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Account {
	
	@Id
	 private String accountNumber;
	 private String username;
	 private String pin;
	 private double balance;

	 @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	 private List<Transaction> transactions=new ArrayList<>();

	 public Account() {
		 super();
		 // TODO Auto-generated constructor stub
	 }
	 //Paramaterised constructor
	 public Account(String accountNumber, String username, String pin, double balance,
				List<Transaction> transactions) {
			super();
			this.accountNumber = accountNumber;
			this.username = username;
			this.pin = pin;
			this.balance = balance;
			this.transactions = transactions;
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
	 public String getPin() {
		return pin;
	 }
	 public void setPin(String pin) {
		this.pin = pin;
	 }
	 public double getBalance() {
		return balance;
	 }
	 public void setBalance(double balance) {
		this.balance = balance;
	 }
	 public List<Transaction> getTransactions() {
		return transactions;
	 }
	 public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	 }
}
