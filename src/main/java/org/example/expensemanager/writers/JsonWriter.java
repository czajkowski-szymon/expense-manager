package org.example.expensemanager.writers;

import org.example.expensemanager.model.transaction.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWriter implements WriterStrategy{
    @Override
    public void write(List<Transaction> transactions, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
