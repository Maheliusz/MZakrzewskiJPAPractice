package pl.edu.agh.ki.bd2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.bd2.dao.SupplierDAO;
import pl.edu.agh.ki.bd2.entity.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class SupplierService implements ISupplierService {
    @Autowired
    private SupplierDAO supplierDAO;

    @Override
    public List<Supplier> getAllSuppliers() {
        EntityManager em = supplierDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        List<Supplier> res;
        try {
            res = em.createQuery("from Company ").getResultList();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return res;
    }

    @Override
    public Supplier getSupplierById(int id) {
        EntityManager em = supplierDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Supplier res;
        try {
            res = (Supplier) em.createQuery("from Company C where C.dbID = :id ")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return res;
    }

    @Override
    public void addSupplier(Supplier supplier) {
        EntityManager em = supplierDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(supplier);
        etx.commit();
    }

    @Override
    public void deleteSupplier(Supplier supplier) {
        EntityManager em = supplierDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.createQuery("delete Company C where C.dbID = :id")
                .setParameter("id", supplier.getDbID()).getSingleResult();
        etx.commit();
    }
}
