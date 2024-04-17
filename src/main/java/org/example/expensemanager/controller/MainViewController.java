package org.example.expensemanager.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.expensemanager.ExpenseManagerApplication;
import org.example.expensemanager.model.Category;
import org.example.expensemanager.model.PaymentMethod;
import org.example.expensemanager.model.transaction.*;
import org.example.expensemanager.repository.*;
import org.example.expensemanager.session.UserSession;
import org.example.expensemanager.writers.ExcelWriter;
import org.example.expensemanager.writers.JsonWriter;
import org.example.expensemanager.writers.WriterService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainViewController {
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField moneyAmountField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> categoriesBox;
    @FXML
    private ComboBox<String> paymentMethodsBox;
    @FXML
    private ComboBox<TransactionType> transactionTypeBox;
    @FXML
    private Button addButton;
    @FXML
    private Label addingErrorLabel;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML
    private TableColumn<Transaction, String> descriptionColumn;
    @FXML
    private TableColumn<Transaction, String> categoryColumn;
    @FXML
    private TableColumn<Transaction, String> paymentColumn;
    @FXML
    private TableColumn<Transaction, BigDecimal> amountColumn;
    @FXML
    private Label balanceLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button excelButton;
    @FXML
    private Button jsonButton;
    private CategoryRepository categoryRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private TransactionRepositoryProxy transactionProxy;
    private UserRepository userRepository;
    private WriterService writer;
    private List<Category> categories = new ArrayList<>();
    private List<PaymentMethod> paymentMethods = new ArrayList<>();
    private ObservableList<Transaction> transactions;

    @FXML
    public void initialize() {
        categoryRepository = new CategoryRepository();
        paymentMethodRepository = new PaymentMethodRepository();
        userRepository = new UserRepository();
        transactionProxy = new TransactionRepositoryProxy();
        categories = categoryRepository.getAllCategories();
        paymentMethods = paymentMethodRepository.getAllPaymentMethods();
        List<String> categoryNames = categories.stream()
                .map(Category::getName)
                .toList();
        List<String> paymentMethodsNames = paymentMethods.stream()
                .map(PaymentMethod::getName)
                .toList();

        categoriesBox.setItems(FXCollections.observableList(categoryNames));
        paymentMethodsBox.setItems(FXCollections.observableList(paymentMethodsNames));
        transactionTypeBox.setItems(FXCollections.observableList(
                Arrays.asList(TransactionType.income, TransactionType.outcome))
        );

        refreshBalance();
        createTable();
    }

    @FXML
    public void onAddButtonClick() {
        if (moneyAmountField.getText().isEmpty() ||
                datePicker.getValue() == null ||
                categoriesBox.getValue() == null ||
                paymentMethodsBox.getValue() == null ||
                transactionTypeBox.getValue() == null) {
            addingErrorLabel.setText("Fill all fields");
            return;
        }

        BigDecimal moneyAmount = null;
        try {
            moneyAmount = new BigDecimal(moneyAmountField.getCharacters().toString());
        } catch (NumberFormatException e) {
            addingErrorLabel.setText("Incorrect number format");
        }
        LocalDate date = datePicker.getValue();
        Category category = getCategory(categoriesBox.getValue());
        PaymentMethod paymentMethod = getPaymentMethods(paymentMethodsBox.getValue());
        String description = descriptionArea.getText();
        TransactionType transactionType = transactionTypeBox.getValue();
        ITransactionBuilder transactionBuilder = new TransactionBuilder();
        Transaction t = transactionBuilder
                .amount(moneyAmount)
                .date(date)
                .category(category)
                .paymentMethod(paymentMethod)
                .description(description)
                .transactionType(transactionType)
                .user(UserSession.getInstance().getUser())
                .build();

        transactionProxy.addTransaction(t);
        UserSession.getInstance().getUser().updateBalance(t);
        userRepository.updateBalanceForUser(UserSession.getInstance().getUser());
        refreshBalance();
        refreshTable();
    }

    @FXML
    public void onLogoutButtonClick() {
        UserSession.getInstance().setUser(null);
        try {
            redirectToAuthView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void writeExcel() {
        writer = new WriterService(
                new ExcelWriter()
        );
        writer.write(transactionProxy.getAllTransactionsForUser(
                UserSession.getInstance().getUser().getUserId()),
                "expenses" + LocalDate.now() + ".xlsx"
        );
    }

    @FXML
    public void writeJson() {
        writer = new WriterService(
                new JsonWriter()
        );
        writer.write(transactionProxy.getAllTransactionsForUser(
                        UserSession.getInstance().getUser().getUserId()),
                "expenses" + LocalDate.now() + ".txt"
        );
    }

    private Category getCategory(String name) {
        Optional<Category> foundCategory = categories.stream()
                .filter(category -> category.getName().equals(name))
                .findFirst();
        return foundCategory.get();
    }

    private PaymentMethod getPaymentMethods(String name) {
        Optional<PaymentMethod> foundPaymentMethod = paymentMethods.stream()
                .filter(paymentMethod -> paymentMethod.getName().equals(name))
                .findFirst();
        return foundPaymentMethod.get();
    }

    private void createTable() {
        dateColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getDate()));
        descriptionColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getDescription()));
        categoryColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getCategory().getName()));
        paymentColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getPaymentMethod().getName()));
        amountColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getAmount()));
        amountColumn.setCellFactory(getAmountCellFactory());

        List<Transaction> transactions = transactionProxy
                .getAllTransactionsForUser(UserSession.getInstance().getUser().getUserId());
        this.transactions = FXCollections.observableList(transactions);
        transactionTable.setItems(this.transactions);
    }

    private void refreshTable() {
        List<Transaction> transactions = transactionProxy
                .getAllTransactionsForUser(UserSession.getInstance().getUser().getUserId());
        this.transactions = FXCollections.observableList(transactions);
        transactionTable.setItems(this.transactions);
    }

    private Callback<TableColumn<Transaction, BigDecimal>, TableCell<Transaction, BigDecimal>> getAmountCellFactory() {
        return column -> new TableCell<Transaction, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal amount, boolean empty) {
                super.updateItem(amount, empty);

                if (amount == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(amount.toString());
                    TransactionType transactionType = getTableRow().getItem().getTransactionType();
                    if (transactionType == TransactionType.income) {
                        setStyle("-fx-background-color: #00FF00;");
                    } else if (transactionType == TransactionType.outcome) {
                        setStyle("-fx-background-color: #FF0000;");
                        amount = amount.negate();
                    } else {
                        setStyle("");
                    }
                    setText(amount.toString());
                }
            }
        };
    }

    private void refreshBalance() {
        balanceLabel.setText(UserSession.getInstance().getUser().getBalance().toString());
    }

    private void redirectToAuthView() throws IOException {
        FXMLLoader loader = new FXMLLoader(ExpenseManagerApplication.class.getResource("auth-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
