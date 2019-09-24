package com.capgemini.pecunia.dao;

public class PassbookMaintenanceQueryMapper {
	
	public static final String QUERY_TRANS_DETAILS = "SELECT trans_id, date, amount, trans_from, trans_to, type, trans_closing_balance FROM transaction JOIN account ON account.account_id= transaction.account_id WHERE transaction.account_id=? AND date BETWEEN account.last_updated and Now()";

	public static final String QUERY_SUMMARY = "SELECT account.account_id, trans_id,date, amount, trans_from,trans_to, type, trans_closing_balance JOIN account ON account.account_id= transaction.account_id WHERE transaction.account_id=? AND date BETWEEN ? and ?";


}
