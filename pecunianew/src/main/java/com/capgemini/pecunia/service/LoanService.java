package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dto.Loan;

public interface LoanService {
	

    public double calculateEMI(double amount, int tenure, double roi);
	
	public boolean createLoanRequest(Loan loan);

	public boolean validateCustomerId(String account_ID);



}		