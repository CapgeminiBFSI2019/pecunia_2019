package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.PassbookMaintenanceService;
import com.capgemini.pecunia.service.PassbookMaintenanceServiceImpl;


public class Demo {
	public static void main(String[] args) throws TransactionException, MyException, ParseException {





//	public static void main(String[] args) throws TransactionException, MyException, ParseException {



		
//		String accountId="100202000001";
//		String type="Debit";
//		String option="Cheque";
//		double amount=200.00;
//		String bankName="Pecunia";
//		String holderName="Anaisha Arora";
//		String ifsc="PBIN0000004";
//		String dateString="2019-09-24";
//		 LocalDate issueDate;
//		 LocalDate transDate=LocalDate.now();
//		// String datePattern="yyyy-mm-dd";
//		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
//		
//		issueDate = LocalDate.parse(dateString);;
//		
//		
//		Transaction trans = new Transaction();
//		Cheque cheque = new Cheque();
//		trans.setAccountId(accountId);
//		trans.setAmount(amount);
//		trans.setOption(option);
//		trans.setType(type);
//		trans.setTransDate(transDate);
//		
//		cheque.setAccountNo(accountId);
//		cheque.setBankName(bankName);
//		cheque.setHolderName(holderName);
//		cheque.setIfsc(ifsc);
//		cheque.setIssueDate(issueDate);
////		cheque.setStatus(Constants.CHEQUE_STATUS_CLEARED);
//		TransactionService transactionService=new TransactionServiceImpl();
//		int transId=transactionService.debitUsingCheque(trans, cheque);
//		System.out.println(transId);
		

		
//		String accountId="100202000001";
//		String type="Credit";
//		String option="Slip";
//		double amount=200.00;
//	
//		
//		String ifsc="PBIN0000004";
//		
//		 
//		 LocalDate transDate=LocalDate.now();
//		
//		 Transaction trans = new Transaction();
//			
//			trans.setAccountId(accountId);
//			trans.setAmount(amount);
//			trans.setOption(option);
//			trans.setType(type);
//			trans.setTransDate(transDate);
//			
//			TransactionService transactionService=new TransactionServiceImpl();
//			int transId=transactionService.creditUsingSlip(trans);
//			System.out.println(transId);

//		Account acc = new Accoun);


//		String accountId="100202000001";
//		String type="Debit";
//		String option="Cheque";
//		double amount=200.00;
//		String bankName="Pecunia";
//		String holderName="Anaisha Arora";
//		String ifsc="PBIN0000004";
//		String dateString="2019-09-24";
//		 LocalDate issueDate;
//		 LocalDate transDate=LocalDate.now();

//		// String datePattern="yyyy-mm-dd";
//		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
//		
//		issueDate = LocalDate.parse(dateString);;
//		
//		
//		Transaction trans = new Transaction();
//		Cheque cheque = new Cheque();
//		trans.setAccountId(accountId);
//		trans.setAmount(amount);
//		trans.setOption(option);
//		trans.setType(type);
//		trans.setTransDate(transDate);
//		
//		cheque.setAccountNo(accountId);
//		cheque.setBankName(bankName);
//		cheque.setHolderName(holderName);
//		cheque.setIfsc(ifsc);
//		cheque.setIssueDate(issueDate);
////		cheque.setStatus(Constants.CHEQUE_STATUS_CLEARED);
//		TransactionService transactionService=new TransactionServiceImpl();
//		int transId=transactionService.debitUsingCheque(trans, cheque);
//		System.out.println(transId);
		

		
		
	}
}
		// String datePattern="yyyy-mm-dd";
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		
//		issueDate = LocalDate.parse(dateString);;

		
//		String name = "saurabh5881@gmail.com";
//		String pwd = "12345";
//		
//		Login login = new Login(name, pwd, null);
//		LoginService loginService = new LoginServiceImpl();
//		boolean vali = loginService.validateEmail(login);
//		System.out.println(vali);
//		
		

//		Transaction trans = new Transaction();
//		Cheque cheque = new Cheque();
//		trans.setAccountId(accountId);
//		trans.setAmount(amount);
//		trans.setOption(option);
//		trans.setType(type);
//		//trans.setTransDate(transDate);
//		
//		cheque.setAccountNo(accountId);
//		cheque.setBankName(bankName);
//		cheque.setHolderName(holderName);
//		cheque.setIfsc(ifsc);
//		cheque.setIssueDate(issueDate);

		
//		TransactionService transactionService=new TransactionServiceImpl();
//		int transId=transactionService.debitUsingCheque(trans, cheque);
//		System.out.println(transId);
		
//		PassbookMaintenanceService transactionService=new PassbookMaintenanceServiceImpl();
//		String sDate1="2019-08-04";
//		String sDate2="2019-11-26";
//		Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(sDate1);
//		Date date2=new SimpleDateFormat("yyyy-mm-dd").parse(sDate2);
//		List<Transaction> transaction = transactionService.accountSummary("100101000001",date1, date2);
//		System.out.println(transaction);
//
//
//		
//		LoanDisbursalService loanDisbursalDAO = new LoanDisbursalServiceImpl();
//		ArrayList<Loan> l = new ArrayList<Loan>();
//		ArrayList<Loan> l1 = new ArrayList<Loan>();	
//		ArrayList<Loan> l3 = new ArrayList<Loan>();	
//		ArrayList<LoanDisbursal> l2 = new ArrayList<LoanDisbursal>();	
//			try {
//				l = loanDisbursalDAO.retrieveAll();
//			} catch (MyException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//				
//					try {
//						loanDisbursalDAO.approveLoan(l);
//					} catch (IOException | MyException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				
//				try {
//					l1 = 	loanDisbursalDAO.rejectedLoanRequests();
//				} catch (MyException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//		
//				
//				
//				try {
//					l2 = loanDisbursalDAO.approvedLoanList();
//				} catch (IOException | MyException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println(l2);
//
//
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("-------------Welcome to Pecunia----------------");
//		System.out.println("1. Login");
//		System.out.println("2. Exit");
//		int choice = scanner.nextInt();
//		do
//		{
//			switch(choice)
//			{
//			case 1:
//				//login function
//				break;
//			case 2:
//				System.exit(1);
//				break;
//			default:
//				System.out.println("Incorrect option");
//			}
//		}
//		while(choice != 2);

		
		


	