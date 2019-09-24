package com.capgemini.pecunia.pl;

import java.sql.Connection;

import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.TransactionDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.DBConnection;

public class Demo {
	public static void main(String[] args) {
		TransactionDAO transactionDAO = new TransactionDAOImpl();
		Account account = new Account();
		account.setId("100431000001");
		try {
			double balance = transactionDAO.getbalance(account);
			System.out.println(balance);
		} catch (MyException | TransactionException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
