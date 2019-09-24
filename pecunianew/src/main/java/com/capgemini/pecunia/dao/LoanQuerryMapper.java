package com.capgemini.pecunia.dao;

public interface LoanQuerryMapper {
	
	
	
	public static final String Add_Loan = "INSERT INTO loan (accountId,amount,creditScore,emi,loan_id,loanStatus,roi,tenure,type) VALUES(?,?,?,?,?,?,?,?,?); ";
	
}
