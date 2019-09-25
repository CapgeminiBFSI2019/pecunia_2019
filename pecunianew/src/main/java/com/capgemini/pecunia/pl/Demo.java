package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dao.LoanDisbursalDAO;
import com.capgemini.pecunia.dao.LoanDisbursalDAOImpl;
import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;

import java.sql.Connection;

import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.DBConnection;


public class Demo {
	public static void main(String[] args) {

//		TransactionDAO  transactionDAO = new TransactionDAOImpl();
//		Account account = new Account();
//		account.setId("100101000001");
//		try {
//			double bal = transactionDAO.getbalance(account);
//			System.out.println(bal);
//		} catch (MyException | TransactionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		LoanDisbursalDAO loanDisbursalDAO = new LoanDisbursalDAOImpl();
		List<Loan> l = new ArrayList<Loan>();
		try {
			l = loanDisbursalDAO.retrieveLoanList();
			System.out.println(l.size());
		    loanDisbursalDAO.releaseLoanSheet(l);
			System.out.println(l);
		} catch (IOException | MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}

}
