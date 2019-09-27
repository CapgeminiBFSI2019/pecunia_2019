package com.capgemini.pecunia.util;

public class Constants {

	public static final String CODE_SAVINGS = "01";
	public static final String CODE_CURRENT = "02";
	public static final String CODE_FD = "03";
	public static final String CODE_LOAN = "04";
	public static final String SAVINGS = "Savings";
	public static final String CURRENT = "Current";
	public static final String FD = "Fd";
	public static final String LOAN = "Loan";
	public static final String[] ACCOUNT_STATUS = {"Active", "Closed"};
	public static final String BANK_NAME = "PECUNIA";
	public static final String TRANSACTION_DEBIT = "DEBIT";
	public static final String TRANSACTION_CREDIT = "CREDIT";
	public static final String TRANSACTION_OPTION_SLIP = "SLIP";
	public static final String TRANSACTION_OPTION_CHEQUE = "CHEQUE";
	public static final String[] OTHER_BANK_NAME = { "ICICI", "SBI", "HDFC", "KOTAK", "AXIS" };
	public static final String[] LOAN_TYPE = { "Personal Loan", "House Loan", "Vehicle Loan", "Jewel Loan"};
	public static final String CHEQUE_STATUS_PENDING = "PENDING";
	public static final String CHEQUE_STATUS_CLEARED = "CLEARED";
	public static final String CHEQUE_STATUS_BOUNCED = "BOUNCED";
	public static final String NA = "-";
	public static final String DATE_FORMAT = "YYYY-MM-DD HH:mm:ss";
	public static final String DATE_FORMAT_1 = "YYYY-MM-DD";
	public static final String INVALID_ACCOUNT_EXCEPTION = "Invalid Account Number";
	public static final String INVALID_BANK_EXCEPTION = "Bank doesn't exist. ";
	public static final String INVALID_CHEQUE_EXCEPTION = "Invalid cheque";
	public static final String EXCEPTION_DURING_TRANSACTION = "Error occured while transaction";
	public static final String INSUFFICIENT_BALANCE_EXCEPTION = "Insufficient balance";
	public static final String CHEQUE_BOUNCE_EXCEPTION = "Cheque bounce due to insufficient balance";
	public static final String TRANSACTION_AMOUNT_ERROR = "The transaction could not take place";
	public static final String LOGIN_FAILURE = "Login unsuccesful";
	public static final String LOGIN_NO_USER_FOUND = "No user found";
	public static final String AMOUNT_EXCEEDS_EXCEPTION = "Credit amount exceeds the maximum amount";
	public static final String AMOUNT_LESS_EXCEPTION = "Credit amount is less than the minimum amount";
	public static final String CONNECTION_FAILURE = "Connection problem. Cannot connect to the database";
	public static final String FILE_CLOSING_FAILURE = "Files cannot be closed";
	public static final String[] LOAN_REQUEST_STATUS = {"Pending","Approved","Rejected"};
	
	public static final double MINIMUM_CREDIT_SLIP_AMOUNT = 100.00;
	public static final double MAXIMUM_CREDIT_SLIP_AMOUNT = 100000.00;
	public static final String SELF_CHEQUE = "SELF";
	public static final double MINIMUM_CHEQUE_AMOUNT = 100.00;
	public static final double MAXIMUM_CHEQUE_AMOUNT = 200000.00;
	public static final double SAVING_ROI = 5.0;
	public static final double CURRENT_ROI = 0.0;
	public static final double FD_ROI = 7.0;
	public static final String NO_LOAN_REQUEST= "No Loan request is present";
}
