package org.example.expensemanager.repository;

import org.example.expensemanager.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends Repository {
    public List<Category> getAllCategories() {
        Connection connection = database.getConnection();
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM category;"
            );
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Category category = new Category();
                category.setCategoryId(result.getLong("category_id"));
                category.setName(result.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getCategoryById(Long categoryId) {
        Connection connection = database.getConnection();
        Category retrievedCategory = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM category WHERE category_id = ?"
            );
            statement.setLong(1, categoryId);
            ResultSet result = statement.executeQuery();
            retrievedCategory = new Category();
            while (result.next()) {
                retrievedCategory.setCategoryId(result.getLong("category_id"));
                retrievedCategory.setName(result.getString("name"));
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedCategory;
    }
}
