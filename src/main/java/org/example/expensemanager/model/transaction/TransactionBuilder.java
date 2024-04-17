package org.example.expensemanager.model.transaction;

import org.example.expensemanager.model.Category;
import org.example.expensemanager.model.PaymentMethod;
import org.example.expensemanager.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionBuilder implements ITransactionBuilder {
    private Long transactionId;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private User user;
    private Category category;
    private PaymentMethod paymentMethod;
    private TransactionType transactionType;

    @Override
    public ITransactionBuilder transactionId(Long transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    @Override
    public ITransactionBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public ITransactionBuilder date(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public ITransactionBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public ITransactionBuilder user(User user) {
        this.user = user;
        return this;
    }

    @Override
    public ITransactionBuilder category(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public ITransactionBuilder paymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    @Override
    public ITransactionBuilder transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    @Override
    public Transaction build() {
        return new Transaction(transactionId, amount, date, description, user, category, paymentMethod, transactionType);
    }
}
