module org.example.expensemanager2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires jbcrypt;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens org.example.expensemanager to javafx.fxml;
    exports org.example.expensemanager;
    exports org.example.expensemanager.controller;
    opens org.example.expensemanager.controller to javafx.fxml;
    opens org.example.expensemanager.model.transaction to com.fasterxml.jackson.databind;
    opens org.example.expensemanager.model to com.fasterxml.jackson.databind;
}