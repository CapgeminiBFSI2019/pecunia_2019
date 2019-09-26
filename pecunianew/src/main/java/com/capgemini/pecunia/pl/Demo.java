package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;
import com.capgemini.pecunia.service.PassbookMaintenanceService;
import com.capgemini.pecunia.service.PassbookMaintenanceServiceImpl;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;
import com.sun.java_cup.internal.runtime.Scanner;


public class Demo {
	public static void main(String[] args) throws TransactionException, MyException, ParseException {

//		LoanDisbursalService loanDisbursalDAO = new LoanDisbursalServiceImpl();
//		ArrayList<Loan> l = new ArrayList<Loan>();
//		ArrayList<Loan> l1 = new ArrayList<Loan>();	
//		ArrayList<Loan> l4 = new ArrayList<Loan>();	
//		ArrayList<Loan> l3 = new ArrayList<Loan>();	
//		
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
//				System.out.println(l1);
//				loanDisbursalDAO.updateLoanStatus(l1);
//		        
				
				

				try {
					try {
						l1 = 	loanDisbursalDAO.rejectedLoanRequests();
					} catch (LoanDisbursalException e) {
						// TODO Auto-generated catch block+
						e.printStackTrace();
					}
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(l1);

				
//				try {
//					l2 = loanDisbursalDAO.approvedLoanList();
//				} catch (IOException | MyException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				//System.out.println(l2);
				

				
				
//				loanDisbursalDAO.updateLoanAccount(l2, 2);
				
				String accountId="100202000001";
				String type="Debit";
				String option="Cheque";
				double amount=500.00;
				String bankName="Pecunia";
				String holderName="Anaisha Arora";
				String ifsc="PBIN0000004";
				String dateString="2019-08-24";
				 LocalDate issueDate;
				 LocalDate transDate=LocalDate.now();
				// String datePattern="yyyy-mm-dd";
				// DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
				
				issueDate = LocalDate.parse(dateString);;
				

				try {
					loanDisbursalDAO.updateExistingBalance(l5);
				} catch (LoanDisbursalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
				TransactionService transactionService=new TransactionServiceImpl();
				int transId=transactionService.debitUsingCheque(trans, cheque);
				System.out.println(transId);



	}
}

		
		


	