package org.example.expensemanager.repository;

import org.example.expensemanager.model.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository {
    public void addUser(User user) {
        Connection connection = database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO expense_manager.`user` (username, password, balance) VALUES(?, ?, ?)"
            );
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBigDecimal(3, BigDecimal.ZERO);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUserName(String username) {
        Connection connection = database.getConnection();
        User retrievedUser = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM user WHERE username = ?"
            );
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            retrievedUser = new User();
            while (result.next()) {
                retrievedUser.setUserId(result.getLong("user_id"));
                retrievedUser.setUsername(result.getString("username"));
                retrievedUser.setPassword(result.getString("password"));
                retrievedUser.setBalance(result.getBigDecimal("balance"));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedUser;
    }

    public void updateBalanceForUser(User user) {
        Connection connection = database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE user SET balance = ? WHERE user_id = ?"
            );
            statement.setBigDecimal(1, user.getBalance());
            statement.setLong(2, user.getUserId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
