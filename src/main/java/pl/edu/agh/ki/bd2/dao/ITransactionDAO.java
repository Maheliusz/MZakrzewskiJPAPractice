package pl.edu.agh.ki.bd2.dao;

import pl.edu.agh.ki.bd2.entity.Product;
import pl.edu.agh.ki.bd2.entity.Transaction;

import java.util.List;

public interface ITransactionDAO {
    public Transaction getTransaction(String id);

    public List<Product> getTransactionProducts(Transaction transaction);

    public int getProductQuantity(Transaction transaction, Product product);
}
