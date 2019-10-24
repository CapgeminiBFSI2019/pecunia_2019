package com.capgemini.pecunia.hibernate.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.entity.LoanRequestEntity;
import com.capgemini.pecunia.entity.TransactionEntity;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PassbookException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.HibernateUtil;

public class PassbookMaintenanceDAOImpl implements PassbookMaintenanceDAO {
	
	private int transId;
	private String type;
	private double amount;
	private String option;
	private LocalDateTime transDate;
	private int chequeId;
	private String transFrom;
	private String transTo;
	private double closingBalance;

	@Override
	public List<com.capgemini.pecunia.dto.Transaction> updatePassbook(String accountId) throws PassbookException, PecuniaException {
		ArrayList<com.capgemini.pecunia.dto.Transaction> transList = new ArrayList<>();
		System.out.println("dao");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			String hql = "from TransactionEntity where accountId= :accountId AND date BETWEEN (SELECT lastUpdated from AccountEntity where accountId= :accountId) and :currentDate";
			Query<TransactionEntity> query = session.createQuery(hql);
			query.setParameter("accountId", accountId);
			query.setParameter("currentDate",java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(330)));
			List<TransactionEntity> results = (List<TransactionEntity>)query.list();
			System.out.println(results);
            System.out.println("****************");
            transList = passbookDetails(results);            
            System.out.println(transList);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
            throw new PassbookException(ErrorConstants.UPDATE_PASSBOOK_ERROR);
        }
	 return transList;
	}
		

	@Override
	public boolean updateLastUpdated(String accountId) throws PecuniaException, PassbookException {
		
		boolean isUpdated = false;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			String hql = "UPDATE AccountEntity SET lastUpdated = :lastUpdated WHERE accountId= :accountId";
			Query query = session.createQuery(hql);
			query.setParameter("accountId", accountId);
			query.setParameter("lastUpdated", java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(330)) );
			int rowsAffected = query.executeUpdate();
			Transaction txn = session.beginTransaction();
			if (rowsAffected > 0) {
				isUpdated = true;
				txn.commit();
			} else {
				throw new PecuniaException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new PassbookException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
		return isUpdated;
	}

	@Override
	public List<com.capgemini.pecunia.dto.Transaction> accountSummary(String accountId, LocalDate startDate, LocalDate endDate)
			throws PassbookException, PecuniaException {
		// TODO Auto-generated method stub
		return null;
	}

	private ArrayList<com.capgemini.pecunia.dto.Transaction> passbookDetails(List<TransactionEntity> res){
		ArrayList<com.capgemini.pecunia.dto.Transaction> transList = new ArrayList<>();
		for(TransactionEntity object : res){
			transId = object.getId();
			type = object.getType();
			amount = object.getAmount();
			option = object.getOption();
			transDate = object.getTransDate();
			chequeId = object.getChequeId();
			transFrom = object.getTransFrom();
			transTo = object.getTransTo();
			closingBalance = object.getClosingBalance();
			
			com.capgemini.pecunia.dto.Transaction transaction = new com.capgemini.pecunia.dto.Transaction(transId, type, amount, option, transDate, chequeId, transFrom, transTo, closingBalance );
			transList.add(transaction);
		}
		
		return transList;
		
	}

}
