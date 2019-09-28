package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.PecuniaException;

public interface LoanService {
	

    public double calculateEMI(double amount, int tenure, double roi);
	
	public boolean createLoanRequest(Loan loan) throws LoanException;




}		