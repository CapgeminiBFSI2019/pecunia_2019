package com.capgemini.pecunia.dao;

public class PassbookMaintenanceQueryMapper {
	public static final String QUERY_ACCOUNT = "SELECT last_updated FROM account WHERE account_id=?";
	public static final String QUERY_TRANS = "SELECT trans_id, date, amount, trans_from, trans_to, type, trans_closing_balance FROM transaction JOIN account ON account.account_id= transaction.account_id WHERE transaction.account_id=? AND date BETWEEN account.last_updated and Now()";
}
