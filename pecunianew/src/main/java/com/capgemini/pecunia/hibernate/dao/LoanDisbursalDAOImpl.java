package com.capgemini.pecunia.hibernate.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.PecuniaException;

public class LoanDisbursalDAOImpl implements LoanDisbursalDAO {

	@Override
	public List<Loan> retrieveLoanList() throws IOException, PecuniaException {
		// TODO Auto-generated method stub
		return null;
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

}
