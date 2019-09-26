package com.capgemini.pecunia.dao;

public interface LoanDisbursalQuerryMapper {
public static final String RETRIVE_ALL_QUERY_FROM_LOAN = "SELECT * FROM loan";
public static final String INSERT_QUERY = "INSERT INTO loan_disbursal(loan_id,account_id,disbursed_amount,due_amount,emi_to_be_paid) VALUES(?,?,?,?,?)";
public static final String RETRIVE_ALL_QUERY_FROM_APPROVED_LOAN = "SELECT * FROM loan_disbursal";
public static final String UPDATE_LOAN_ACCOUNT = "UPDATE loan_disbursal SET due_amount = ? , emi_to_be_paid = ? WHERE account_id = ?";
public static final String UPDATE_LOAN_STATUS = "UPDATE loan SET loan_status = ?  WHERE account_id = ?";
public static final String UPDATE_AMOUNT = "UPDATE account SET balance = ? WHERE account_id = ?";

}

//
//Full texts	
//loan_id
//account_id Ascending 1
//amount
//type
//tenure
//roi
//loan_status
//emi
//credit_score