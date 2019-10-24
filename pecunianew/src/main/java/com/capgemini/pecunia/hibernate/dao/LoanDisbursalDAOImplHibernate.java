package com.capgemini.pecunia.hibernate.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.entity.LoanRequestEntity;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.HibernateUtil;

public class LoanDisbursalDAOImplHibernate implements LoanDisbursalDAO {
	
	private int loanId;
	private String accountId;
	private Double amount;
	private String type;
	private int tenure;
	private double roi;
	private String status;
	private Double emi;
	private int creditScore;

	@Override
	public List<Loan> retrieveLoanList() throws IOException, PecuniaException, LoanDisbursalException {
		ArrayList<Loan> reqList = new ArrayList<>();
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            String hql = "FROM LoanRequestEntity";
	            Query<LoanRequestEntity> query = session.createQuery(hql);
	            List<LoanRequestEntity> results = query.list();
	            System.out.println(results);
	            System.out.println("****************");
	          
	            reqList = loanRequests(results);            
	            System.out.println(reqList);
	            }
		 catch(Exception e) {
	            throw new LoanDisbursalException(ErrorConstants.NO_LOAN_REQUESTS);
	        }
		 return reqList;
	}

	@Override
	public void releaseLoanSheet(ArrayList<Loan> loanList) throws IOException, PecuniaException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<LoanDisbursal> loanApprovedList() throws IOException, PecuniaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateLoanAccount(ArrayList<LoanDisbursal> loanApprovals, double dueAmount, double tenure,
			String accountId) throws IOException, PecuniaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStatus(ArrayList<Loan> loanRequests, int loanID, String Status)
			throws IOException, PecuniaException {
		// TODO Auto-generated method stub

	}

	@Override
	public double totalEmi(String accountId) throws PecuniaException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Loan> retrieveAcceptedLoanListWithoutStatus() throws IOException, PecuniaException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> uniqueIds() throws IOException, PecuniaException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private ArrayList<Loan> loanRequests(List<LoanRequestEntity> res){
		ArrayList<Loan> reqList = new ArrayList<>();
		for(LoanRequestEntity obj : res){
			loanId  = obj.getLoanId();
			accountId = obj.getAccountId();
			amount = obj.getAmount();
			type = obj.getType();
			tenure = obj.getTenure();
			roi = obj.getRoi();
			status = obj.getStatus();
			emi = obj.getEmi();
			creditScore = obj.getCreditScore();
			Loan loan = new Loan(loanId, accountId, amount, type, tenure, roi, status, emi, creditScore);
			reqList.add(loan);
		}
		
		return reqList;
		
	}

}
