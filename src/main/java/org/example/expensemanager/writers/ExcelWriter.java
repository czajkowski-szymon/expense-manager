package org.example.expensemanager.writers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.expensemanager.model.transaction.Transaction;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter implements WriterStrategy {
    @Override
    public void write(List<Transaction> transactions, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Transactions");
            String[] headers = {"Date", "Description", "Category", "Payment Method", "Amount", "Transaction Type"};
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            for (int rowIndex = 0; rowIndex < transactions.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                Transaction transaction = transactions.get(rowIndex);

                row.createCell(0).setCellValue(transaction.getDate().toString());
                row.createCell(1).setCellValue(transaction.getDescription());
                row.createCell(2).setCellValue(transaction.getCategory().getName());
                row.createCell(3).setCellValue(transaction.getPaymentMethod().getName());
                row.createCell(4).setCellValue(transaction.getAmount().doubleValue());
                row.createCell(5).setCellValue(transaction.getTransactionType().name());
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
