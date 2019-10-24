package com.capgemini.pecunia.hibernate.dao;

import javax.persistence.NamedQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.entity.AccountEntity;
import com.capgemini.pecunia.entity.TransactionEntity;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.HibernateUtil;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public double getBalance(Account account) throws PecuniaException, TransactionException {

//		org.hibernate.Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // start a transaction
//            transaction = session.beginTransaction();
//            // save the student object
//            Query<TransactionEntity> query = session.createNamedQuery(name);("from TransactionEntity where id=:transactionId");
//            query.setParameter("transactionId", 200);
//            query.setMaxResults(1);
////            TransactionEntity mytransaction = (TransactionEntity) query.uniqueResult();
////            System.out.println(mytransaction.getAmount());
//            query.getResultList();
//            // commit transaction
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }

		double accountBalance = -1;
		org.hibernate.Transaction transaction = null;
		try {
			String accountId = account.getId();
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = session.createNamedQuery("AccountEntity.getBalanceById");
			query.setParameter("accountId", accountId);
			query.setMaxResults(1);
			AccountEntity accountEntity = (AccountEntity) query.uniqueResult();
			if (accountEntity != null) {
				accountBalance = accountEntity.getBalance();
			} else {
				throw new PecuniaException(ErrorConstants.NO_SUCH_ACCOUNT);
			}

			transaction.commit();
			session.close();
		} catch (Exception e) {
			throw new PecuniaException(e.getMessage());
		}
		return accountBalance;
	}

	@Override
	public boolean updateBalance(Account account) throws PecuniaException, TransactionException {
		boolean balanceUpdated = false;
		org.hibernate.Transaction tx = null;
		try {
			String accountId = account.getId();
			double newBalance = account.getBalance();
			Session session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			AccountEntity accountEntity = session.load(AccountEntity.class, accountId);
			accountEntity.setBalance(newBalance);
			session.update(accountEntity);
			
			if(accountEntity.getBalance()==newBalance) {
				balanceUpdated = true;
			}
			else {
				throw new TransactionException(ErrorConstants.BALANCE_UPDATE_ERROR);
			}
			tx.commit();
			session.close();
		} catch (Exception e) {
				throw new TransactionException(e.getMessage());
		}
		return balanceUpdated;
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
