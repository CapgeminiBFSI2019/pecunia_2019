package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.PecuniaException;

public interface LoanDAO {
	public boolean addLoanDetails(Loan loan) throws PecuniaException, LoanException;
}
