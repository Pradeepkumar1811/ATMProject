package com.ATMProject.atm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ATMProject.atm.entities.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	 @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
	 Account findByAccountNumber(@Param("accountNumber") String accountNumber);
	  @Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.pin = :pin")
	 Account findByAccountNumberAndPin(@Param("accountNumber") String accountNumber, @Param("pin") String pin);
}
