package pl.edu.agh.ki.bd2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.ki.bd2.entity.Address;
import pl.edu.agh.ki.bd2.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Transactional
public class CompanyDAO implements ICompanyDAO {
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public Company getCompany(String name) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Company company;
        try {
            company = (Company) em.createQuery("from Company as C where C.name=:name")
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e){
            etx.rollback();
            return null;
        }
        etx.commit();
        return company;
    }

    @Override
    public Address getAddress(Company company) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Company c;
        try {
            c = (Company) em.createQuery("from Company as C where C.dbID=:id")
                    .setParameter("id", company.getDbID()).getSingleResult();
        } catch (Exception e){
            etx.rollback();
            return null;
        }
        etx.commit();
        return c.getAddress();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
}
