package com.capgemini.pecunia.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.entity.TransactionEntity;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.HibernateUtil;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public double getBalance(Account account) throws PecuniaException, TransactionException {
		org.hibernate.Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            Query query = session.createQuery("from TransactionEntity where id=:transactionId");
            query.setParameter("transactionId", 200);
            query.setMaxResults(1);
            TransactionEntity mytransaction = (TransactionEntity) query.uniqueResult();
            System.out.println(mytransaction.getAmount());
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
		
		return 0;
	}

	@Override
	public boolean updateBalance(Account account) throws PecuniaException, TransactionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int generateChequeId(Cheque cheque) throws PecuniaException, TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateTransactionId(Transaction transaction) throws PecuniaException, TransactionException {
		// TODO Auto-generated method stub
		return 0;
	}

}
