package org.example.expensemanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database dbInstance = null;
    private final String url = "jdbc:mariadb://localhost:3306/expense_manager";
    private final String user = "root";
    private final String password = "root";
    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (dbInstance == null) {
            dbInstance = new Database();
        }
        return dbInstance;
    }

    public Connection getConnection() {
        return connection;
    }
}
