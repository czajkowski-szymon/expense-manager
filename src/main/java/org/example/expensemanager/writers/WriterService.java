package org.example.expensemanager.writers;

import org.example.expensemanager.model.transaction.Transaction;

import java.util.List;

public class WriterService {
    private WriterStrategy writer;

    public WriterService(WriterStrategy writer) {
        this.writer = writer;
    }

    public void write(List<Transaction> transactions, String filePath) {
        writer.write(transactions, filePath);
    }
}
