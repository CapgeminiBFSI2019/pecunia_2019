package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.MyException;

public interface LoanDAO {
	public void addLoanDetails(Loan loan) throws MyException, LoanException ;
	

}
   