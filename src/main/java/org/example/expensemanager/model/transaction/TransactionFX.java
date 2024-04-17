package org.example.expensemanager.model.transaction;

import javafx.beans.property.StringProperty;

public class TransactionFX {
    private StringProperty amount;
    private StringProperty date;
    private StringProperty description;
    private StringProperty category;
    private StringProperty paymentMethod;
    private StringProperty transactionType;

    public TransactionFX(StringProperty amount,
                         StringProperty date,
                         StringProperty description,
                         StringProperty category,
                         StringProperty paymentMethod,
                         StringProperty transactionType) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.transactionType = transactionType;
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getPaymentMethod() {
        return paymentMethod.get();
    }

    public StringProperty paymentMethodProperty() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod.set(paymentMethod);
    }

    public String getTransactionType() {
        return transactionType.get();
    }

    public StringProperty transactionTypeProperty() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType.set(transactionType);
    }
}
