package org.example.expensemanager.repository;

import org.example.expensemanager.db.Database;

public class Repository {
    protected Database database;

    public Repository() {
        database = Database.getInstance();
    }
}
