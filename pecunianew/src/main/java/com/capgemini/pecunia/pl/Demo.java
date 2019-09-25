package com.capgemini.pecunia.pl;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

public class Demo {
	public static void main(String[] args) throws TransactionException, MyException {
		TransactionService transactionService = new TransactionServiceImpl();
//		Account account = new Account();
//		account.setId("100431000001");
//		try {
//			double balance = transactionDAO.getbalance(account);
//			System.out.println(balance);
//		} catch (MyException | TransactionException e) {
//			System.out.println(e.getMessage());
//		}
//		
		String accountId="100202000001";
		String type="Debit";
		String option="Cheque";
		double amount=200.00;
		String bankName="Pecunia";
		String holderName="Anaisha Arora";
		String ifsc="PBIN0000004";
		String dateString="2019-09-24";
		 LocalDate issueDate;
		 LocalDate transDate=LocalDate.now();
		// String datePattern="yyyy-mm-dd";
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
		
		issueDate = LocalDate.parse(dateString);;
		
		
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
		
		int transId=transactionService.debitusingCheque(trans, cheque);
		System.out.println(transId);
	}
}
