package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import com.capgemini.pecunia.dao.LoanDisbursalDAO;
import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;

public class Demo {
	public static void main(String[] args) {


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
				
				l3 = loanDisbursalDAO.rejectedRequestsList(rejectedList)();
				
				
				try {
					l2 = loanDisbursalDAO.approvedLoanList();
				} catch (IOException | MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(l2);


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
	
	public boolean login(String email,String password) throws MyException
	{
		boolean flag=false,loginSuccess=false;
		
		return flag;
	}
}


