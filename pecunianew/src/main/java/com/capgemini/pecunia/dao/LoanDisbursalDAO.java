package com.capgemini.pecunia.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.PecuniaException;

public interface LoanDisbursalDAO {
	public List<Loan> retrieveLoanList() throws IOException, PecuniaException;

	public void releaseLoanSheet(List<Loan> loanList) throws IOException, PecuniaException;

	public ArrayList<LoanDisbursal> loanApprovedList() throws IOException, PecuniaException;

	public void updateLoanAccount(ArrayList<LoanDisbursal> loanApprovals, double dueAmount, double tenure,
			String accountId) throws IOException, PecuniaException;

	public void updateStatus(ArrayList<Loan> loanRequests, String accountId, String Status)
			throws IOException, PecuniaException;

}
