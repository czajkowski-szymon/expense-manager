package org.example.expensemanager.writers;

import org.example.expensemanager.model.transaction.Transaction;

import java.util.List;

public interface WriterStrategy {
    void write(List<Transaction> transactions, String filePath);
}
