package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;

public interface LoanDisbursalService {
	public ArrayList<Loan> retrieveAll() throws PecuniaException, IOException, LoanDisbursalException;

	public ArrayList<Loan> rejectedLoanRequests() throws PecuniaException, LoanDisbursalException;

	public ArrayList<Loan> approveLoan(ArrayList<Loan> l) throws IOException, PecuniaException, LoanDisbursalException;

	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, PecuniaException;

	public String updateLoanAccount(ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths)
			throws PecuniaException, LoanDisbursalException;

	public String updateLoanStatus(ArrayList<Loan> rejectedLoanList, ArrayList<Loan> approvedLoanList) throws PecuniaException, LoanDisbursalException;

	public String updateExistingBalance(ArrayList<Loan> approvedLoanList)
			throws PecuniaException, TransactionException, LoanDisbursalException;

}
