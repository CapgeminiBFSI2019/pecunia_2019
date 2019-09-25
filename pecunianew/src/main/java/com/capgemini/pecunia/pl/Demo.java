package com.capgemini.pecunia.pl;

import java.util.Scanner;


import com.capgemini.pecunia.dao.LoanDisbursalDAO;
import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;


import com.capgemini.pecunia.exception.MyException;

public class Demo {
	public static void main(String[] args) {


		LoanDisbursalDAO loanDisbursalDAO = new LoanDisbursalDAOImpl();
		List<Loan> l = new ArrayList<Loan>();
		try {
			l = loanDisbursalDAO.retrieveLoanList();
			loanDisbursalDAO.releaseLoanSheet(l);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}


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


