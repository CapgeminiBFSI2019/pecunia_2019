package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.LoanException;
import com.capgemini.pecunia.exception.MyException;

public interface LoanDAO {
	public boolean addLoanDetails(Loan loan) throws MyException, LoanException ;
    
	public String fetchAccountId(String accountId) throws MyException, LoanException;
	

}
   