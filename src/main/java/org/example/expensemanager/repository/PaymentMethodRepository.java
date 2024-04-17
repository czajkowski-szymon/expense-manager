package org.example.expensemanager.repository;

import org.example.expensemanager.model.PaymentMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodRepository extends Repository {
    public List<PaymentMethod> getAllPaymentMethods() {
        Connection connection = database.getConnection();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM payment_method;"
            );
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setPaymentMethodId(result.getLong("payment_method_id"));
                paymentMethod.setName(result.getString("name"));
                paymentMethods.add(paymentMethod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentMethods;
    }

    public PaymentMethod getPaymentMethodById(Long paymentMethodId) {
        Connection connection = database.getConnection();
        PaymentMethod retrievedPaymentMethod = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM payment_method WHERE payment_method_id = ?"
            );
            statement.setLong(1, paymentMethodId);
            ResultSet result = statement.executeQuery();
            retrievedPaymentMethod = new PaymentMethod();
            while (result.next()) {
                retrievedPaymentMethod.setPaymentMethodId(result.getLong("payment_method_id"));
                retrievedPaymentMethod.setName(result.getString("name"));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedPaymentMethod;
    }
}
