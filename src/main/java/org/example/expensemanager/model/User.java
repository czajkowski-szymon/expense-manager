package org.example.expensemanager.model;

import org.example.expensemanager.model.transaction.Transaction;
import org.example.expensemanager.model.transaction.TransactionType;

import java.math.BigDecimal;
import java.util.Objects;

public class User {
    private Long userId;
    private String username;
    private String password;
    private BigDecimal balance;

    public User() {}

    public User(Long userId, String username, String password, BigDecimal balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public User(String username, String password, BigDecimal balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void updateBalance(Transaction transaction) {
        if (transaction.getTransactionType() == TransactionType.income) {
            balance = balance.add(transaction.getAmount());
        } else {
            balance = balance.subtract(transaction.getAmount());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
