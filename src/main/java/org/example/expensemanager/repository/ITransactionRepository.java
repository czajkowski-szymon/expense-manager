package org.example.expensemanager.repository;

import org.example.expensemanager.model.transaction.Transaction;

import java.util.List;

public interface ITransactionRepository {
    List<Transaction> getAllTransactionsForUser(Long userId);
    void addTransaction(Transaction transaction);

}
