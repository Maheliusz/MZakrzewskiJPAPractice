package pl.edu.agh.ki.bd2.service;

import pl.edu.agh.ki.bd2.entity.Product;

import java.util.List;

public interface IProductService {
    public List<Product> getAllProducts();

    public Product getProductById(int id);

    public void deleteProduct(Product product);

    public void addProduct(Product product);
}
