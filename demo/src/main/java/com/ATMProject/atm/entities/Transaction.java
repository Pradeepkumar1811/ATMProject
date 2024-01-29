package com.ATMProject.atm.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;
@Entity
public class Transaction {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private double amount;
	 private LocalDateTime timestamp;

	 @ManyToOne
	 @JoinColumn(name = "account_number")
	 private Account account;

	 public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	 }

	 public Transaction(Long id, double amount, LocalDateTime timestamp, Account account) {
		super();
		this.id = id;
		this.amount = amount;
		this.timestamp = timestamp;
		this.account = account;
	 }

	 
	public Transaction(double amount, Account account) {
		super();
		this.amount = amount;
		this.account = account;
	}

	// getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
