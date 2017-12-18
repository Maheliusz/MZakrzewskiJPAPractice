package pl.edu.agh.ki.bd2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.bd2.entity.Category;
import pl.edu.agh.ki.bd2.entity.Product;
import pl.edu.agh.ki.bd2.entity.Supplier;
import pl.edu.agh.ki.bd2.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ProductDAO implements IProductDAO {
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public Product findProductByName(String name) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Product product;
        try {
            product = (Product) em.createQuery("from Product as P where P.name=:name")
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return product;
    }

    @Override
    public List<Transaction> getProductTransactions(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Product p;
        try {
            p = (Product) em.createQuery("from Product P where P.dbID = :id")
                    .setParameter("id", product.getDbID()).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return p.getTransactionList();
    }

    @Override
    public Category getProductCategory(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Product p;
        try {
            p = (Product) em.createQuery("from Product P where P.dbID = :id")
                    .setParameter("id", product.getDbID()).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return p.getCategory();
    }

    @Override
    public int getProductAmount(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Product p;
        try {
            p = (Product) em.createQuery("from Product P where P.dbID = :id")
                    .setParameter("id", product.getDbID()).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return 0;
        }
        etx.commit();
        return p.getOnStock();
    }

    @Override
    public Supplier getProductSupplier(Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Product p;
        try {
            p = (Product) em.createQuery("from Product P where P.dbID = :id")
                    .setParameter("id", product.getDbID()).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return p.getSupplier();
    }

    @Override
    public List<Product> getAllProducts() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        List<Product> p;
        try {
            p = (List<Product>) em.createQuery("from Product P").getResultList();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return p;
    }

    @Override
    public Product getProductById(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Product p;
        try {
            p = (Product) em.createQuery("from Product P where P.dbID = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return p;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
