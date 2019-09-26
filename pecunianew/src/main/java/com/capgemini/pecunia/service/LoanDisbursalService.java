package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;

public interface LoanDisbursalService {
	public  ArrayList<Loan> retrieveAll() throws MyException, IOException, LoanDisbursalException ;
	public ArrayList<Loan> rejectedLoanRequests() throws MyException, LoanDisbursalException;
	public ArrayList<Loan> approveLoan(ArrayList<Loan> l) throws IOException, MyException, LoanDisbursalException;
	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, MyException; 


	public void updateLoanAccount(ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths) throws MyException;
	



	

	public void updateLoanStatus(ArrayList<Loan> rejectedLoanList, ArrayList<Loan> approvedLoanList) throws MyException;
	
	public void updateExistingBalance(ArrayList<Loan> approvedLoanList) throws MyException, TransactionException, LoanDisbursalException;


	
	
}
