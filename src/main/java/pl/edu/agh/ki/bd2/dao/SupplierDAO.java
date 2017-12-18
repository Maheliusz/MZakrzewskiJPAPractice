package pl.edu.agh.ki.bd2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.bd2.entity.Product;
import pl.edu.agh.ki.bd2.entity.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SupplierDAO extends CompanyDAO implements ISupplierDAO {
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public int getBankAccountNumber(Supplier supplier) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Supplier s;
        try {
            s = (Supplier) em.createQuery("from Company as C where C.dbID=:id")
                    .setParameter("id", supplier.getDbID()).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return 0;
        }
        etx.commit();
        return s.getBankAccountNumber();
    }

    @Override
    public List<Product> getProductsSupplied(Supplier supplier) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Supplier s;
        try {
            s = (Supplier) em.createQuery("from Company as C where C.dbID=:id")
                    .setParameter("id", supplier.getDbID()).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return s.getProducts();
    }

    @Override
    public Supplier getCompany(String name) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Supplier supplier;
        try {
            supplier = (Supplier) em.createQuery("from Company as C where C.name=:name")
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return supplier;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
