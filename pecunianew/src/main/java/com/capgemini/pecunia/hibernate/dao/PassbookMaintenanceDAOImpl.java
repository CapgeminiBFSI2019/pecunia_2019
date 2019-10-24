package com.capgemini.pecunia.hibernate.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.PassbookException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.HibernateUtil;

public class PassbookMaintenanceDAOImpl implements PassbookMaintenanceDAO {

	@Override
	public List<Transaction> updatePassbook(String accountId) throws PassbookException, PecuniaException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			String hql = "from transaction where account_id= :accountId AND date BETWEEN (SELECT last_updated from account where account_id= :accountId) and CONVERT_TZ(NOW(),'+00:00','+05:30')";
			Query query = session.createQuery(hql);
			query.setParameter("accountId", accountId);
			query.list();
			Transaction txn = session.beginTransaction();
			
		

	@Override
	public boolean updateLastUpdated(String accountId) throws PecuniaException, PassbookException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Transaction> accountSummary(String accountId, LocalDate startDate, LocalDate endDate)
			throws PassbookException, PecuniaException {
		// TODO Auto-generated method stub
		return null;
	}

}
