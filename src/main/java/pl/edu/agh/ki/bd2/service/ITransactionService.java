package pl.edu.agh.ki.bd2.service;

import pl.edu.agh.ki.bd2.entity.Transaction;

import java.util.List;

public interface ITransactionService {
    public List<Transaction> getAllTransactions();

    public Transaction getTransactionById(int id);

    public void deleteTransaction(Transaction transaction);

    public void addTransaction(Transaction transaction);
}
