package pl.edu.agh.ki.bd2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.bd2.entity.Product;
import pl.edu.agh.ki.bd2.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TransactionDAO implements ITransactionDAO {
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public Transaction getTransaction(String id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Transaction transaction;
        try {
            transaction=(Transaction) em.createQuery("from Transaction as T where T.dbID=:id")
                .setParameter("id", id).getSingleResult();
        } catch (Exception e){
            etx.rollback();
            return null;
        }
        etx.commit();
        return transaction;
    }

    @Override
    public List<Product> getTransactionProducts(Transaction transaction) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Transaction t;
        try {
            t = (Transaction) em.createQuery("from Transaction as T where T.dbID=:id")
                    .setParameter("id", transaction.getDbID()).getSingleResult();
        }catch (Exception e){
            etx.rollback();
            return null;
        }
        return t.getProducts();
    }

    @Override
    public int getProductQuantity(Transaction transaction, Product product) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Transaction t;
        try {
            t = (Transaction) em.createQuery("from Transaction as T where T.dbID=:id")
                    .setParameter("id", transaction.getDbID()).getSingleResult();
        }catch (Exception e){
            etx.rollback();
            return 0;
        }
        return t.getQuantity().get(product);
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
