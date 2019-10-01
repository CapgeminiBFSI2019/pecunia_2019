package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;


public interface LoanService {
	

    public double calculateEMI(double amount, int tenure, double roi);
	
	public boolean createLoanRequest(Loan loan) throws LoanException;




}		