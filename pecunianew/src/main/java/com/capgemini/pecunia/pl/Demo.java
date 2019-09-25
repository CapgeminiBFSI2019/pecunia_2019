package com.capgemini.pecunia.pl;


import java.io.IOException;
import java.util.ArrayList;


import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;
import com.capgemini.pecunia.util.Constants;
import com.capgemini.pecunia.util.DBConnection;




import java.util.Scanner;


import com.capgemini.pecunia.dao.LoanDisbursalDAO;
import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;

public class Demo {

//	public static void main1(String[] args) {
//
//

	public static void main(String[] args) throws TransactionException, MyException, LoanDisbursalException {


		
		String accountId="100202000001";
		String type="Debit";
		String option="Cheque";
		double amount=200.00;
		String bankName="Pecunia";
		String holderName="Anaisha Arora";
		String ifsc="PBIN0000004";
		String dateString="2019-09-24";
		 LocalDate issueDate;
		 LocalDate transDate=LocalDate.now();
		// String datePattern="yyyy-mm-dd";
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		
		issueDate = LocalDate.parse(dateString);;
		
		
		Transaction trans = new Transaction();
		Cheque cheque = new Cheque();
		trans.setAccountId(accountId);
		trans.setAmount(amount);
		trans.setOption(option);
		trans.setType(type);
		trans.setTransDate(transDate);
		
		cheque.setAccountNo(accountId);
		cheque.setBankName(bankName);
		cheque.setHolderName(holderName);
		cheque.setIfsc(ifsc);
		cheque.setIssueDate(issueDate);
//		cheque.setStatus(Constants.CHEQUE_STATUS_CLEARED);
		TransactionService transactionService=new TransactionServiceImpl();
		int transId=transactionService.debitUsingCheque(trans, cheque);
		System.out.println(transId);
		
		LoanDisbursalService loanDisbursalDAO = new LoanDisbursalServiceImpl();
		ArrayList<Loan> l = new ArrayList<Loan>();
		ArrayList<Loan> l1 = new ArrayList<Loan>();	
		ArrayList<Loan> l3 = new ArrayList<Loan>();	
		ArrayList<LoanDisbursal> l2 = new ArrayList<LoanDisbursal>();	
			try {
				l = loanDisbursalDAO.retrieveAll();
			} catch (MyException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
				
					try {
						loanDisbursalDAO.approveLoan(l);
					} catch (IOException | MyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				try {
					l1 = 	loanDisbursalDAO.rejectedLoanRequests();
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
				
				
				try {
					l2 = loanDisbursalDAO.approvedLoanList();
				} catch (IOException | MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(l2);


		Scanner scanner = new Scanner(System.in);
		System.out.println("-------------Welcome to Pecunia----------------");
		System.out.println("1. Login");
		System.out.println("2. Exit");
		int choice = scanner.nextInt();
		do
		{
			switch(choice)
			{
			case 1:
				//login function
				break;
			case 2:
				System.exit(1);
				break;
			default:
				System.out.println("Incorrect option");
			}
		}
		while(choice != 2);

		
		


	}
}


