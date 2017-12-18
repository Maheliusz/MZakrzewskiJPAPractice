package pl.edu.agh.ki.bd2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.bd2.dao.CompanyDAO;
import pl.edu.agh.ki.bd2.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class CompanyService implements ICompanyService {
    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public List<Company> getCompanyList() {
        EntityManager em = companyDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        List<Company> res;
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
    public Company getCompanyById(int id) {
        EntityManager em = companyDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Company res;
        try {
            res = (Company) em.createQuery("from Company C where C.dbID = :id ")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            etx.rollback();
            return null;
        }
        etx.commit();
        return res;
    }

    @Override
    public void addCompany(Company company) {
        EntityManager em = companyDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(company);
        etx.commit();
    }

    @Override
    public void deleteCompany(Company company) {
        EntityManager em = companyDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.createQuery("delete Company C where C.dbID = :id")
                .setParameter("id", company.getDbID()).getSingleResult();
        etx.commit();
    }
}
