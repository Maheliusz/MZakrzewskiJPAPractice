package pl.edu.agh.ki.bd2.service;

import pl.edu.agh.ki.bd2.entity.Customer;

import java.util.List;

public interface ICustomerService {
    public List<Customer> getAllCustomers();

    public Customer getCustomerById(int id);

    public void addCustomer(Customer customer);

    public void deleteCustomer(Customer customer);
}
