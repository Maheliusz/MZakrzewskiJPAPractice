package pl.edu.agh.ki.bd2.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Customer extends Company {
    private double discount;

    @OneToMany
    private List<Transaction> transactions = new LinkedList<>();

    public Customer() {
    }

    public Customer(String name, String city, String street, String zipcode, double discount) {
        super(name, city, street, zipcode);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
