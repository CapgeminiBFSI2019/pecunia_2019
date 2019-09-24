package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Loan;

public interface LoanDAO {
	public void addLoanDetails(Loan loan) ;//throws LoanException;
	public void viewLoanDetails(Loan loan) ;//throws LoanException;

}
   