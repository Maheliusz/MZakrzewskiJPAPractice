package pl.edu.agh.ki.bd2.dao;

import pl.edu.agh.ki.bd2.entity.Category;
import pl.edu.agh.ki.bd2.entity.Product;
import pl.edu.agh.ki.bd2.entity.Supplier;
import pl.edu.agh.ki.bd2.entity.Transaction;

import java.util.List;

public interface IProductDAO {
    public Product findProductByName(String name);

    public List<Transaction> getProductTransactions(Product product);

    public Category getProductCategory(Product product);

    public int getProductAmount(Product product);

    public Supplier getProductSupplier(Product product);

    public List<Product> getAllProducts();

    public Product getProductById(int id);
}
