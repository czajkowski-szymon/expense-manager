package org.example.expensemanager.repository;

import org.example.expensemanager.model.transaction.ITransactionBuilder;
import org.example.expensemanager.model.transaction.Transaction;
import org.example.expensemanager.model.transaction.TransactionBuilder;
import org.example.expensemanager.model.transaction.TransactionType;
import org.example.expensemanager.session.UserSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class TransactionRepository extends Repository implements ITransactionRepository {
    private CategoryRepository categoryRepository;
    private PaymentMethodRepository paymentMethodRepository;

    public TransactionRepository(CategoryRepository categoryRepository, PaymentMethodRepository paymentMethodRepository) {
        this.categoryRepository = categoryRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public List<Transaction> getAllTransactionsForUser(Long userId) {
        Connection connection = database.getConnection();
        List<Transaction> transactions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM transaction WHERE user_id = ?;"
            );
            statement.setLong(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ITransactionBuilder transactionBuilder = new TransactionBuilder();
                Transaction transaction = transactionBuilder
                        .amount(result.getBigDecimal("amount"))
                        .date(toLocalDate(result.getDate("date")))
                        .description(result.getString("description"))
                        .user(UserSession.getInstance().getUser())
                        .category(categoryRepository.getCategoryById(result.getLong("category_id")))
                        .paymentMethod(paymentMethodRepository
                                .getPaymentMethodById(result.getLong("payment_method_id")))
                        .transactionType(TransactionType.valueOf(result.getString("type")))
                        .build();
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Connection connection = database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO expense_manager.`transaction`\n" +
                            "(amount, `date`, description, user_id, category_id, payment_method_id, `type`)\n" +
                            "VALUES(?, ?, ?, ?, ?, ?, ?);"
            );
            statement.setBigDecimal(1, transaction.getAmount());
            statement.setDate(2, toDate(transaction.getDate()));
            statement.setString(3, transaction.getDescription());
            statement.setLong(4, transaction.getUser().getUserId());
            statement.setLong(5, transaction.getCategory().getCategoryId());
            statement.setLong(6, transaction.getPaymentMethod().getPaymentMethodId());
            statement.setString(7, transaction.getTransactionType().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private Date toDate(LocalDate date) {
        return Date.valueOf(date);
    }
}
