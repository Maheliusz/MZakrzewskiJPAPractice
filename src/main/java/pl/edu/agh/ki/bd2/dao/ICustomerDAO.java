package pl.edu.agh.ki.bd2.dao;

import pl.edu.agh.ki.bd2.entity.Customer;
import pl.edu.agh.ki.bd2.entity.Transaction;

import java.util.List;

public interface ICustomerDAO extends ICompanyDAO {
    public double getDiscount(Customer customer);

    public List<Transaction> getTransactions(Customer customer);

    @Override
    public Customer getCompany(String name);
}
