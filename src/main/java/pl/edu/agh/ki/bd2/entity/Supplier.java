package pl.edu.agh.ki.bd2.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Supplier extends Company {
    private int bankAccountNumber;

    @OneToMany
    private List<Product> products = new LinkedList<>();

    public Supplier() {
    }

    public Supplier(String name, String city, String street, String zipcode, int number) {
        super(name, city, street, zipcode);
        this.bankAccountNumber = number;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
        product.setSupplier(this);
    }
}
