package pl.edu.agh.ki.bd2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.bd2.entity.Customer;
import pl.edu.agh.ki.bd2.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CustomerDAO extends CompanyDAO implements ICustomerDAO {
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public double getDiscount(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Customer c;
        try {
            c = (Customer) em.createQuery("from Company as C where C.dbID=:id")
                    .setParameter("id", customer.getDbID()).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return 0.0;
        }
        etx.commit();
        return c.getDiscount();
    }

    @Override
    public List<Transaction> getTransactions(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Customer c;
        try {
            c = (Customer) em.createQuery("from Company as C where C.dbID=:id")
                    .setParameter("id", customer.getDbID()).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return c.getTransactions();
    }

    @Override
    public Customer getCompany(String name) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Customer customer;
        try {
            customer = (Customer) em.createQuery("from Company as C where C.name=:name")
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return customer;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
