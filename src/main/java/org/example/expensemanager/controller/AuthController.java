package org.example.expensemanager.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.expensemanager.ExpenseManagerApplication;
import org.example.expensemanager.exception.UserWithGivenNameAlreadyExistException;
import org.example.expensemanager.model.User;
import org.example.expensemanager.repository.UserRepository;
import org.example.expensemanager.service.AuthService;
import org.example.expensemanager.session.UserSession;

import java.io.IOException;

public class AuthController {
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private TextField registerTextField;
    @FXML
    private PasswordField registerPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Label loginLabel;
    @FXML
    private Label registerLabel;
    private AuthService authService;

    @FXML
    public void initialize() {
        authService = new AuthService(
                new UserRepository()
        );
    }

    @FXML
    public void onLoginButtonClick() {
        String username = loginTextField.getCharacters().toString();
        String password = loginPasswordField.getCharacters().toString();
        if (username.isEmpty() || password.isEmpty()) {
            loginLabel.setText("Podaj poprawne dane");
        } else {
            User user = new User(username, password);
            User currentUser = authService.authenticate(user);
            if (currentUser != null) {
                UserSession userSession = UserSession.getInstance();
                userSession.setUser(currentUser);
                try {
                    redirectToMainView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                loginLabel.setText("Bledne login lub haslo");
            }
        }
    }

    @FXML
    public void onRegisterButtonClick() {
        String username = registerTextField.getCharacters().toString();
        String password = registerPasswordField.getCharacters().toString();
        if (username.isEmpty() || password.isEmpty()) {
            registerLabel.setText("Podaj poprawne dane");
        } else {
            User user = new User(username, password);
            try {
                authService.register(user);
            } catch (UserWithGivenNameAlreadyExistException e) {
                registerLabel.setText(e.getMessage());
            }

        }
        registerTextField.clear();
        registerPasswordField.clear();
    }

    private void redirectToMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(ExpenseManagerApplication.class.getResource("main-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}