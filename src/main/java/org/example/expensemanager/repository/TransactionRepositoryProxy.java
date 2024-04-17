package org.example.expensemanager.repository;

import org.example.expensemanager.model.User;
import org.example.expensemanager.model.transaction.Transaction;
import org.example.expensemanager.session.UserSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepositoryProxy implements ITransactionRepository {

    private final ITransactionRepository transactionRepository;
    private Map<User, List<Transaction>> transactions;

    public TransactionRepositoryProxy() {
        transactionRepository = new TransactionRepository(
                new CategoryRepository(), new PaymentMethodRepository()
        );
        transactions = new HashMap<>();
    }

    @Override
    public List<Transaction> getAllTransactionsForUser(Long userId) {
        List<Transaction> retrievedTransactions = transactionRepository.getAllTransactionsForUser(userId);
        transactions.put(UserSession.getInstance().getUser(), retrievedTransactions);
        return retrievedTransactions;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.get(UserSession.getInstance().getUser()).add(transaction);
        transactionRepository.addTransaction(transaction);
    }
}
