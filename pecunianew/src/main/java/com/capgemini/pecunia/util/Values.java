package com.capgemini.pecunia.util;

public class Values {
	public static final String BANK_NAME = "PECUNIA";
	public static final String TRANSACTION_DEBIT = "DEBIT";
	public static final String TRANSACTION_CREDIT = "CREDIT";
	public static final String TRANSACTION_OPTION_SLIP = "SLIP";
	public static final String TRANSACTION_OPTION_CHEQUE = "CHEQUE";
	public static final String[] OTHER_BANK_NAME = { "ICICI", "SBI", "HDFC", "KOTAK", "AXIS" };
	public static final String CHEQUE_STATUS_PENDING = "PENDING";
	public static final String CHEQUE_STATUS_CLEARED = "CLEARED";
	public static final String CHEQUE_STATUS_BOUNCED = "BOUNCED";
	public static final String NA = "-";
	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	public static final String INVALID_ACCOUNT_EXCEPTION = "Invalid Account Number";
	public static final String INVALID_BANK_EXCEPTION = "Bank doesn't exist";
	public static final String EXCEPTION_DURING_TRANSACTION = "Error occured while transaction";
	public static final String INSUFFICIENT_BALANCE_EXCEPTION = "Insufficient balance";
	public static final String CHEQUE_BOUNCE_EXCEPTION = "Cheque bounce due to insufficient balance";
	public static final String AMOUNT_EXCEEDS_EXCEPTION = "Credit amount exceeds the maximum amount";
	public static final String AMOUNT_LESS_EXCEPTION = "Credit amount is less than the minimum amount";

	public static final String LOGIN_FAILURE = "Login unsuccesful";
	public static final String LOGIN_NO_USER_FOUND = "No user found";
}
