package pl.edu.agh.ki.bd2.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Product {
    public Integer getDbID() {
        return dbID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dbID;

    private String name;
    private int onStock;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToMany
    private List<Transaction> transactionList = new LinkedList<>();

    public Product(){}

    public Product(String name, int onStock){
        this.name=name;
        this.onStock=onStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addCategory(Category category){
        setCategory(category);
        category.getProducts().add(this);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
        transaction.getProducts().add(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getOnStock() {
        return onStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOnStock(int onStock) {
        this.onStock = onStock;
    }
}
