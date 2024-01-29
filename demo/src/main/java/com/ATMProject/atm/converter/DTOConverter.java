package com.ATMProject.atm.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ATMProject.atm.entities.*;
import com.ATMProject.atm.model.*;

@Component
public class DTOConverter {
	public static AccountDTO convertAccountEntityToDTO(Account accountEntity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(accountEntity.getAccountNumber());
        accountDTO.setPin(accountEntity.getPin());
        accountDTO.setUsername(accountEntity.getUsername());
        accountDTO.setBalance(accountEntity.getBalance());

        // Convert and set transactions
        List<TransactionDTO> transactionDTOs = accountEntity.getTransactions().stream()
                .map(DTOConverter::convertTransactionEntityToDTO)
                .collect(Collectors.toList());
        accountDTO.setTransactions(transactionDTOs);

        return accountDTO;
    }

    public static TransactionDTO convertTransactionEntityToDTO(Transaction transactionEntity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAccount(transactionEntity.getAccount());
        transactionDTO.setAmount(transactionEntity.getAmount());
        transactionDTO.setTimestamp(transactionEntity.getTimestamp());
        return transactionDTO;
    }

    public static Account convertDTOToAccountEntity(AccountDTO accountDTO) {
        Account accountEntity = new Account();
        accountEntity.setAccountNumber(accountDTO.getAccountNumber());
        accountEntity.setPin(accountDTO.getPin());
        accountEntity.setUsername(accountDTO.getUsername());
        accountEntity.setBalance(accountDTO.getBalance());

        // Convert and set transactions
        List<Transaction> transactionEntities = accountDTO.getTransactions().stream()
        .map(dto -> convertDTOToTransactionEntity(dto, accountEntity))
        .collect(Collectors.toList());
accountEntity.setTransactions(transactionEntities);

        return accountEntity;
    }

    public static Transaction convertDTOToTransactionEntity(TransactionDTO transactionDTO, Account accountEntity) {
        Transaction transactionEntity = new Transaction();
        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setTimestamp(transactionDTO.getTimestamp());
        transactionEntity.setAccount(accountEntity);
        return transactionEntity;
    }

}
