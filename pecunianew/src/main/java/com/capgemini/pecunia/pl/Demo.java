package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

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
import com.sun.java_cup.internal.runtime.Scanner;


public class Demo {
	public static void main(String[] args) throws TransactionException, MyException, ParseException {

		LoanDisbursalService loanDisbursalDAO = new LoanDisbursalServiceImpl();
		ArrayList<Loan> l = new ArrayList<Loan>();
		ArrayList<Loan> l1 = new ArrayList<Loan>();	
		ArrayList<Loan> l4 = new ArrayList<Loan>();	
		ArrayList<Loan> l3 = new ArrayList<Loan>();	
		ArrayList<Loan> l5 = new ArrayList<Loan>();	
		
		ArrayList<LoanDisbursal> l2 = new ArrayList<LoanDisbursal>();	
			try {
				try {
					l = loanDisbursalDAO.retrieveAll();
				} catch (LoanDisbursalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (MyException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
				
					try {
						try {
							l5 = loanDisbursalDAO.approveLoan(l);
						} catch (LoanDisbursalException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (IOException | MyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				try {
					try {
						l1 = 	loanDisbursalDAO.rejectedLoanRequests();
					} catch (LoanDisbursalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(l1);
				
		        
				
				
				
				try {
					l2 = loanDisbursalDAO.approvedLoanList();
				} catch (IOException | MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				loanDisbursalDAO.updateLoanStatus(l1,l5);
				//System.out.println(l2);
				

				
				
				loanDisbursalDAO.updateLoanAccount(l2, 2);
				
		



	}
}

		
		


	