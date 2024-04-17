package org.example.expensemanager.model.transaction;

import org.example.expensemanager.model.Category;
import org.example.expensemanager.model.PaymentMethod;
import org.example.expensemanager.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ITransactionBuilder {
    ITransactionBuilder transactionId(Long transactionId);
    ITransactionBuilder amount(BigDecimal amount);
    ITransactionBuilder date(LocalDate date);
    ITransactionBuilder description(String description);
    ITransactionBuilder user(User user);
    ITransactionBuilder category(Category category);
    ITransactionBuilder paymentMethod(PaymentMethod paymentMethod);
    ITransactionBuilder transactionType(TransactionType transactionType);
    Transaction build();
}
