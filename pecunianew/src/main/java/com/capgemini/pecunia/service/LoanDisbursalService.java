package com.capgemini.pecunia.service;

import java.io.IOException;
import java.util.ArrayList;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.MyException;

public interface LoanDisbursalService {
	public  ArrayList<Loan> retrieveAll() throws MyException, IOException ;
	public ArrayList<Loan> rejectedLoanRequests() throws MyException;
	public void approveLoan(ArrayList<Loan> l) throws IOException, MyException;
	public ArrayList<LoanDisbursal> approvedLoanList() throws IOException, MyException; 
	public ArrayList<Loan> rejectedRequestsList(ArrayList<Loan> rejectedList) throws IOException, MyException; 
	
}
