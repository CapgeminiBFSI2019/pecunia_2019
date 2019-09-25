package com.capgemini.pecunia.pl;


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
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.LoginService;
import com.capgemini.pecunia.service.LoginServiceImpl;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;
import com.capgemini.pecunia.util.Constants;
import com.capgemini.pecunia.util.DBConnection;



import java.util.Scanner;


import com.capgemini.pecunia.dao.LoanDisbursalDAO;
import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.Login;
import com.capgemini.pecunia.exception.MyException;

public class Demo {
	public static void main(String[] args) throws MyException, LoginException {
		
		String name = "saurabh5881@gmail.com";
		String pwd = "12345";
		
		Login login = new Login(name, pwd, null);
		LoginService loginService = new LoginServiceImpl();
		boolean vali = loginService.validateEmail(login);
		System.out.println(vali);
		
		
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
//		
//		
////		LoanDisbursalDAO loanDisbursalDAO = new LoanDisbursalDAOImpl();
////		List<Loan> l = new ArrayList<Loan>();
////		try {
////			l = loanDisbursalDAO.retrieveLoanList();
////			loanDisbursalDAO.releaseLoanSheet(l);
////		} catch (IOException | MyException e) {
////			e.printStackTrace();
////		}
////
////
////		Scanner scanner = new Scanner(System.in);
////		System.out.println("-------------Welcome to Pecunia----------------");
////		System.out.println("1. Login");
////		System.out.println("2. Exit");
////		int choice = scanner.nextInt();
////		do
////		{
////			switch(choice)
////			{
////			case 1:
////				//login function
////				break;
////			case 2:
////				System.exit(1);
////				break;
////			default:
////				System.out.println("Incorrect option");
////			}
////		}
////		while(choice != 2);
////		
////	}
////	
////	public boolean login(String email,String password) throws MyException
////	{
////		boolean flag=false,loginSuccess=false;
////		
////		return flag;

	}
}


