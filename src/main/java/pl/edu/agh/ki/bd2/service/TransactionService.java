package pl.edu.agh.ki.bd2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.bd2.dao.TransactionDAO;
import pl.edu.agh.ki.bd2.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionDAO transactionDAO;

    @Override
    public List<Transaction> getAllTransactions() {
        EntityManager em = transactionDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        List<Transaction> res = em.createQuery("from Transaction").getResultList();
        etx.commit();
        return res;
    }

    @Override
    public Transaction getTransactionById(int id) {
        EntityManager em = transactionDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        Transaction res = (Transaction) em.createQuery("from Transaction T where t.dbID = :id")
                .setParameter("id", id).getSingleResult();
        etx.commit();
        return res;
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        EntityManager em = transactionDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.createQuery("delete Transaction T where t.dbID = :id")
                .setParameter("id", transaction.getDbID()).getSingleResult();
        etx.commit();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        EntityManager em = transactionDAO.getEmf().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(transaction);
        etx.commit();
    }
}
