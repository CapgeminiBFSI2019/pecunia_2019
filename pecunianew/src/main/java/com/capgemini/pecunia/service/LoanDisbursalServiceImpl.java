package com.capgemini.pecunia.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class LoanDisbursalServiceImpl implements LoanDisbursalService {
	private ArrayList<Loan> rejectedLoanList = new ArrayList<Loan>();

	public ArrayList<Loan> retrieveAll() throws MyException, IOException, LoanDisbursalException {

		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<Loan> retrievedLoanRequests = new ArrayList<Loan>();
		retrievedLoanRequests = (ArrayList<Loan>) loanDisbursedDAO.retrieveLoanList();
		if(retrievedLoanRequests.size()==0) {
			throw new LoanDisbursalException("No loan request is present in database");
		}
		return retrievedLoanRequests;
	}

	public void approveLoan(ArrayList<Loan> loanRequestList) throws IOException, MyException, LoanDisbursalException 
	{
		int size = loanRequestList.size();
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		if(loanRequestList.size()==0) {
	    	throw new LoanDisbursalException("No loan request has been rejected");
	    }
		if (size > 0) {
			for (int i = 0; i < loanRequestList.size(); i++) {
				String loanStatus = loanRequestList.get(i).getLoanStatus();
				int creditScore = loanRequestList.get(i).getCreditScore();
				if (creditScore < 670 || loanStatus != "Pending") {

					rejectedLoanList.add(loanRequestList.get(i));
					loanRequestList.remove(i);
				}

			}

			loanDisbursedDAO.releaseLoanSheet(loanRequestList);
		}
		
	
	}

	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, MyException {
		LoanDisbursalDAOImpl loanDisbursedDAO = new LoanDisbursalDAOImpl();
		ArrayList<LoanDisbursal> approvedLoanList = new ArrayList<LoanDisbursal>();
		approvedLoanList = loanDisbursedDAO.loanApprovedList();
		return approvedLoanList;

	}

	public ArrayList<Loan> rejectedLoanRequests() throws MyException, LoanDisbursalException {
	    if(rejectedLoanList.size()==0) {
	    	throw new LoanDisbursalException("No loan request has been rejected");
	    }
		return rejectedLoanList;
	}
	
	public ArrayList<Loan> rejectedRequestsList(ArrayList<Loan> rejectedLoanList) throws IOException, MyException{
		for (int i = 0; i < rejectedLoanList.size(); i++) {
			if (rejectedLoanList.get(i).getLoanStatus() == "Pending") {
				rejectedLoanList.remove(i);
				Loan update = new Loan(rejectedLoanList.get(i).getLoanId(), rejectedLoanList.get(i).getAccountId(),
						rejectedLoanList.get(i).getAmount(), rejectedLoanList.get(i).getType(),
						rejectedLoanList.get(i).getTenure(), rejectedLoanList.get(i).getRoi(),
						"Rejected", rejectedLoanList.get(i).getEmi(),
						rejectedLoanList.get(i).getCreditScore());
				rejectedLoanList.add(i,update);
			}
			
	}
		
		
		return rejectedLoanList;

}
	public void updateLoanAccount(ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths) throws MyException {
		Connection connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;

		for (int i = 0; i < updateLoanApprovals.size(); i++) {
			double updatedDueAmount = updateLoanApprovals.get(i).getDisbursedAmount()
					- (updateLoanApprovals.get(i).getDisbursedAmount()
							/ updateLoanApprovals.get(i).getNumberOfEmiToBePaid()) * numberOfMonths;
			double updatedTenure = updateLoanApprovals.get(i).getNumberOfEmiToBePaid() - numberOfMonths;
		}
	}
}
