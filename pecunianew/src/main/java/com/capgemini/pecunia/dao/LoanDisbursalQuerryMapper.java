package com.capgemini.pecunia.dao;

public interface LoanDisbursalQuerryMapper {
public static final String RETRIVE_ALL_QUERY_FROM_LOAN = "SELECT * FROM loan";
public static final String INSERT_QUERY = "INSERT INTO loan_disbursal(loan_id,account_id,disbursed_amount,due_amount,emi_to_be_paid) VALUES(?,?,?,?,?)";
public static final String RETRIVE_ALL_QUERY_FROM_APPROVED_LOAN = "SELECT * FROM loan_disbursal";
}