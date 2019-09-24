package com.capgemini.pecunia.dao;

public interface LoanQuerryMapper {
	
	
	
	public static final String ADD_LOAN_DETAILS = "INSERT INTO loan (loan_id,account_id,amount,type,tenure,roi,loan_status,emi,credit_score) VALUES(?,?,?,?,?,?,?,?,?) ";
	
	
	public static final String FETCH_ACCOUNT_ID = "SELECT account_id FROM account WHERE account_id=?";
	
	
}
