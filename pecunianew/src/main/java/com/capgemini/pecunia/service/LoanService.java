package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dto.Loan;

public interface LoanService {
    public double calculateEMI(double amount, int tenure, double roi);
	
	public String createLoanRequest(Loan loan);

	public boolean validateCustomerId(Loan loan);
}		