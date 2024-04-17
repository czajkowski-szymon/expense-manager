package org.example.expensemanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExpenseManagerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExpenseManagerApplication.class.getResource("auth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("Expense Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}