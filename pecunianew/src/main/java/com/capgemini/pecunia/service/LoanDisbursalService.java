package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.MyException;

public interface LoanDisbursalService {
	public  ArrayList<Loan> retrieveAll() throws MyException, IOException, LoanDisbursalException ;
	public ArrayList<Loan> rejectedLoanRequests() throws MyException, LoanDisbursalException;
	public void approveLoan(ArrayList<Loan> l) throws IOException, MyException, LoanDisbursalException;
	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, MyException; 

//	public ArrayList<Loan> rejectedRequestsList(ArrayList<Loan> rejectedList) throws IOException, MyException;
	public void updateLoanAccount(ArrayList<LoanDisbursal> updateLoanApprovals, int numberOfMonths) throws MyException;
	public void updateLoanStatus(ArrayList<Loan> rejectedLoanList) throws MyException;


	public ArrayList<Loan> rejectedRequestsList(ArrayList<Loan> rejectedList) throws IOException, MyException;
	

	
	
}
