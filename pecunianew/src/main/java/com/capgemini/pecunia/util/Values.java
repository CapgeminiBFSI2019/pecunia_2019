package com.capgemini.pecunia.util;

public class Values {
	public static final String BANK_NAME = "PECUNIA";
	public static final String TRANSACTION_DEBIT = "DEBIT";
	public static final String TRANSACTION_CREDIT = "CREDIT";
	public static final String TRANSACTION_OPTION_SLIP = "SLIP";
	public static final String TRANSACTION_OPTION_CHEQUE = "CHEQUE";
	public static final String[] OTHER_BANK_NAME = {"ICICI","SBI","HDFC","KOTAK","AXIS"};
	public static final String CHEQUE_STATUS_PENDING = "PENDING";
	public static final String CHEQUE_STATUS_CLEARED = "CLEARED";
	public static final String CHEQUE_STATUS_BOUNCED = "BOUNCED";
	public static final String NA = "-";
	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	public static final String ACCOUNT_CSV_FILE = "src/main/java/com/capgemini/pecunia/dao/DbFiles/Account.csv";
	public static final String ADDRESS_CSV_FILE = "src/main/java/com/capgemini/pecunia/dao/DbFiles/Address.csv";
	public static final String BRANCH_CSV_FILE = "src/main/java/com/capgemini/pecunia/dao/DbFiles/Branch.csv";
	public static final String CUSTOMER_CSV_FILE = "src/main/java/com/capgemini/pecunia/dao/DbFiles/Customer.csv";
	public static final String LOAN_REQUEST_CSV_FILE = "src/main/java/com/capgemini/pecunia/dao/DbFiles/LoanRequest.csv";
	public static final String TRANSACTION_CSV_FILE = "src/main/java/com/capgemini/pecunia/dao/DbFiles/Transaction.csv";
	public static final String EMPLOYEE_CSV_FILE = "src/main/java/com/capgemini/pecunia/dao/DbFiles/Employee.csv";
	public static final String LOAN_REQUEST_CSV_FILE1 = "src/main/java/com/capgemini/pecunia/dao/DbFiles/LoanRequest1.csv";
	public static final String LOAN_DISBURSED_CSV_FILE = "src/main/java/com/capgemini/pecunia/dao/DbFiles/LoanDisbursed.csv";
	public static final String INVALID_ACCOUNT_EXCEPTION = "Invalid Account Number";
	public static final String INVALID_BANK_EXCEPTION = "Invalid bank ";
	public static final String EXCEPTION_DURING_TRANSACTION = "Error occured while transaction";
	public static final String INSUFFICIENT_BALANCE_EXCEPTION = "Insufficient balance";
	public static final String CHEQUE_BOUNCE_EXCEPTION = "Cheque bounce due to insufficient balance";
	
//	public static final String LOGIN_SUCCESS = "Login succesful";
	public static final String LOGIN_FAILURE = "Login unsuccesful";
	public static final String LOGIN_NO_USER_FOUND = "No user found";
}
