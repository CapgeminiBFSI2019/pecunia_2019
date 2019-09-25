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
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.TransactionService;
import com.capgemini.pecunia.service.TransactionServiceImpl;
import com.capgemini.pecunia.util.DBConnection;



import java.util.Scanner;


import com.capgemini.pecunia.dao.LoanDisbursalDAO;
import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dto.Loan;


import com.capgemini.pecunia.exception.MyException;

public class Demo {
	public static void main(String[] args) throws TransactionException, MyException {

		
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


