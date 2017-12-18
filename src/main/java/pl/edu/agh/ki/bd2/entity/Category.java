package pl.edu.agh.ki.bd2.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dbID;

    private String name;

    @OneToMany
    private List<Product> products = new LinkedList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void addToCategory(Product product){
        products.add(product);
        product.setCategory(this);
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDbID() {
        return dbID;
    }
}
