package org.example.expensemanager.exception;

public class UserWithGivenNameAlreadyExistException extends RuntimeException {
    public UserWithGivenNameAlreadyExistException(String message) {
        super(message);
    }
}
