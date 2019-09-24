package com.capgemini.pecunia.dao;

public interface LoanDisbursalQuerryMapper {
public static final String RETRIVE_ALL_QUERY = "SELECT 	loan_id,account_id,amount, type, tenure, roi, loan_status, emi, credit_score FROM loan";
public static final String INSERT_QUERY = "INSERT INTO loan_disbursal(loan_id,account_id,disbursed_amount,due_amount,emi_to_be_paid) VALUES(?,?,?,?,?)";
}