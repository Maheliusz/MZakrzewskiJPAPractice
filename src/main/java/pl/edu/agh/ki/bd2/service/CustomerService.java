package pl.edu.agh.ki.bd2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.bd2.dao.CompanyDAO;
import pl.edu.agh.ki.bd2.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class CustomerService implements ICustomerService  {
    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager em = companyDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        List<Customer> res;
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
    public Customer getCustomerById(int id) {
        EntityManager em = companyDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Customer res;
        try {
            res = (Customer) em.createQuery("from Company C where C.dbID = :id ")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return res;
    }

    @Override
    public void addCustomer(Customer customer) {
        EntityManager em = companyDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(customer);
        etx.commit();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        EntityManager em = companyDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.createQuery("delete Company C where C.dbID = :id")
                .setParameter("id", customer.getDbID()).getSingleResult();
        etx.commit();
    }
}
