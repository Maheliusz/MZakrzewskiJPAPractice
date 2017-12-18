package pl.edu.agh.ki.bd2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.bd2.dao.ProductDAO;
import pl.edu.agh.ki.bd2.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public void deleteProduct(Product product) {
        EntityManager em =  productDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.createQuery("delete from Product P where P.dbID = :id").setParameter("id", product.getDbID());
        etx.commit();
    }

    @Override
    public void addProduct(Product product) {
        EntityManager em =  productDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(product);
        etx.commit();
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
