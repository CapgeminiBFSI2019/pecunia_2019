package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;


import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;

import java.util.List;

import java.io.IOException;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.dto.Login;

import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.LoginException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.hibernate.dao.LoanDisbursalDAOImplHibernate;
import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.hibernate.dao.AccountManagementDAO;
import com.capgemini.pecunia.hibernate.dao.AccountManagementDAOImpl;
import com.capgemini.pecunia.hibernate.dao.TransactionDAOImpl;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;
import com.capgemini.pecunia.service.LoanDisbursalServiceImpl;
import com.capgemini.pecunia.service.LoginServiceImpl;

public class PecuniaMain {

	public static void main(String[] args) throws PecuniaException, TransactionException {

//			throws PecuniaException, TransactionException, IOException, LoanDisbursalException {
////
//
//			throws PecuniaException, TransactionException, IOException, LoanDisbursalException, LoginException {
//		try
//		{
//			Login login = new Login("anish@gmail.com","11112345");
//			boolean loginSuccess = new LoginServiceImpl().validateEmail(login);
//			System.out.println(loginSuccess);
//		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//
////		LoanDisbursalDAOImplHibernate l = new LoanDisbursalDAOImplHibernate();
////		l.retrieveLoanList();
////		AccountManagementService accService = new AccountManagementServiceImpl();
//
//		LoanDisbursalServiceImpl l  = new LoanDisbursalServiceImpl();
//	    System.out.println(l.approveLoan());
//	}
		com.capgemini.pecunia.hibernate.dao.TransactionDAO trans = new TransactionDAOImpl();
		Account acc = new Account();
		acc.setId("100101000001");
		double bal = trans.getBalance(acc);
		System.out.println("balance:"+bal);
}
}
