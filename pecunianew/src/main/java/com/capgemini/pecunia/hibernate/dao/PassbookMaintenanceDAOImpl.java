package com.capgemini.pecunia.hibernate.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.Transaction;
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
	public List<Transaction> updatePassbook(String accountId) throws PassbookException, PecuniaException {
		ArrayList<Transaction> transList = new ArrayList<>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			String hql = "from transaction where account_id= :accountId AND date BETWEEN (SELECT last_updated from account where account_id= :accountId) and CONVERT_TZ(NOW(),'+00:00','+05:30')";
			Query<TransactionEntity> query = session.createQuery(hql);
			query.setParameter("accountId", accountId);
		
			List<TransactionEntity> results = query.list();
			System.out.println(results);
            System.out.println("****************");
            transList = passbookDetails(results);            
            System.out.println(transList);
		}
		catch(Exception e) {
            throw new PassbookException(ErrorConstants.UPDATE_PASSBOOK_ERROR);
        }
	 return transList;
	}
		

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

	private ArrayList<Transaction> passbookDetails(List<TransactionEntity> res){
		ArrayList<Transaction> transList = new ArrayList<>();
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
			
			Transaction transaction = new Transaction(transId, type, amount, option, transDate, chequeId, transFrom, transTo, closingBalance );
			transList.add(transaction);
		}
		
		return transList;
		
	}

}
