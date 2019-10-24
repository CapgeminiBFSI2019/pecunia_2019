package com.capgemini.pecunia.pl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import com.capgemini.pecunia.dto.Loan;
import com.capgemini.pecunia.dto.LoanDisbursal;
import com.capgemini.pecunia.exception.LoanDisbursalException;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.hibernate.dao.LoanDisbursalDAOImplHibernate;

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

public class PecuniaMain {

	public static void main(String[] args)
			throws PecuniaException, TransactionException, IOException, LoanDisbursalException {

		LoanDisbursalDAOImplHibernate l = new LoanDisbursalDAOImplHibernate();
		l.retrieveLoanList();
//		AccountManagementService accService = new AccountManagementServiceImpl();

//		Account account = new Account();
//		Customer customer = new Customer();
//		account.setId("100303000001");
//		customer.setName("Jordi bhaiYYA");
//		try {
//			accService.updateCustomerName(account, customer);
//		} catch (AccountException e) {
//			System.out.println(e.getMessage());
//		}
		
//		Address address = new Address();
//		address.setLine1("Near sangam");
//		address.setLine2("mnnit");
//		address.setCity("Prayagraj");
//		address.setState("UP");
//		address.setCountry("Bharat");
//		address.setZipcode("211004");
//		customer.setAadhar("986006541237");
//		customer.setContact("8700012888");
//		customer.setGender("F");
//		customer.setName("linette");
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		customer.setDob(LocalDate.parse("1996-05-08", dateTimeFormatter));
//		customer.setPan("MADSF6798L");
//		account.setAccountType("FD");
//		account.setBalance(1500);
//		account.setBranchId("1002");
//		account.setInterest(6);
//		account.setLastUpdated(LocalDateTime.now());
//		String accountId = accService.addAccount(customer,address,account);
//		if (accountId != null) {
//			System.out.println("Account created, account Id is :" + accountId);
//		} else {
//			System.out.println(ErrorConstants.ACCOUNT_CREATION_ERROR);
//		}
//		Account account = new Account();
//		account.setId("100303000001");
//		Address address = new Address();
//		address.setLine1("yar");
//		address.setLine2("nahi");
//		address.setCity("hai");
//		address.setState("new");
//		address.setCountry("India");
//		address.setZipcode("522001");
//		try {
//			accService.updateCustomerAddress(account, address);
//		} catch (AccountException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		AccountManagementDAO accDAO = new AccountManagementDAOImpl();
//		Account account = new Account();
//		account.setId("100303000001");
//		try {
//			if(accService.deleteAccount(account)) {
//				System.out.println("Deleted");
//			}
//			else {
//				System.out.println("Some Error");
//			}
//		} catch (AccountException e) {
//			e.printStackTrace();
//		}
//		
//		TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
//		transactionDAOImpl.getBalance(null);
//		
//		AccountManagementDAO accDAO = new AccountManagementDAOImpl();
//		Account account = new Account();
//		account.setId("100102000006");
//		Customer customer = new Customer();
//		customer.setName("Aditiii");
//		try {
//			accDAO.updateCustomerName(account, customer);
//		} catch (AccountException e) {
//			System.out.println(e.getMessage());
//		}

//		try {
//			TransactionDAOImpl transactionDAOImpl = new TransactionDAOImpl();
//			Account account2 = new Account();
//			account2.setId("100102000006");
//			account2.setBalance(10000.0);
//			boolean balanceUpdated = transactionDAOImpl.updateBalance(account2);
//			System.out.println(balanceUpdated);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}

}
