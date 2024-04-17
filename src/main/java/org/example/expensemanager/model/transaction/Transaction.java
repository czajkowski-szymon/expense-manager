package org.example.expensemanager.model.transaction;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.example.expensemanager.model.Category;
import org.example.expensemanager.model.PaymentMethod;
import org.example.expensemanager.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Transaction {
    @JsonIgnore
    private Long transactionId;
    @JsonProperty("amount")
    @JsonIgnore
    private BigDecimal amount;
    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    @JsonProperty("description")
    private String description;
    @JsonIgnore
    private User user;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("paymentMethod")
    private PaymentMethod paymentMethod;
    @JsonProperty("transactionType")
    private TransactionType transactionType;

    public Transaction() {}

    public Transaction(Long expenseId,
                       BigDecimal amount,
                       LocalDate date,
                       String description,
                       User user,
                       Category category,
                       PaymentMethod paymentMethod,
                       TransactionType transactionType) {
        this.transactionId = expenseId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.user = user;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.transactionType = transactionType;
    }

    public Transaction(BigDecimal amount,
                       LocalDate date,
                       String description,
                       User user,
                       Category category,
                       PaymentMethod paymentMethod,
                       TransactionType transactionType) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.user = user;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.transactionType = transactionType;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
