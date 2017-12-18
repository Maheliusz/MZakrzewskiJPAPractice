package pl.edu.agh.ki.bd2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Transaction {
    public Integer getDbID() {
        return dbID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dbID;

    @ElementCollection
    private Map<Product, Integer> quantity = new HashMap<>();

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    public Transaction(){}


    public Map<Product, Integer> getQuantity() {
        return quantity;
    }

    public int getQuantity(Product product){
        return quantity.get(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
        product.getTransactionList().add(this);
    }
}
