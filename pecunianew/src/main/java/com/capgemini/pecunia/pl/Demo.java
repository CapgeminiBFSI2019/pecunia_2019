package com.capgemini.pecunia.pl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.service.PassbookMaintenanceService;
import com.capgemini.pecunia.service.PassbookMaintenanceServiceImpl;

public class Demo {
	public static void main(String[] args) throws TransactionException, MyException, ParseException {

		
//		String accountId="100202000001";
//		String type="Debit";
//		String option="Cheque";
//		double amount=200.00;
//		String bankName="Pecunia";
//		String holderName="Anaisha Arora";
//		String ifsc="PBIN0000004";
//		String dateString="2019-09-24";
//		 LocalDate issueDate;
//		 LocalDate transDate=LocalDate.now();
//		// String datePattern="yyyy-mm-dd";
//		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
//		
//		issueDate = LocalDate.parse(dateString);;
//		
//		
//		Transaction trans = new Transaction();
//		Cheque cheque = new Cheque();
//		trans.setAccountId(accountId);
//		trans.setAmount(amount);
//		trans.setOption(option);
//		trans.setType(type);
//		trans.setTransDate(transDate);
//		
//		cheque.setAccountNo(accountId);
//		cheque.setBankName(bankName);
//		cheque.setHolderName(holderName);
//		cheque.setIfsc(ifsc);
//		cheque.setIssueDate(issueDate);
////		cheque.setStatus(Constants.CHEQUE_STATUS_CLEARED);
//		TransactionService transactionService=new TransactionServiceImpl();
//		int transId=transactionService.debitUsingCheque(trans, cheque);
//		System.out.println(transId);
		
//		PassbookMaintenanceService PassbookService=new PassbookMaintenanceServiceImpl();
//		List <Transaction> updatePassbook = new ArrayList<Transaction>();
//		updatePassbook	= PassbookService.updatePassbook("100202000001");
//		
//		
//		for(int i = 0;i<updatePassbook.size();i++)
//		{
//			System.out.print(updatePassbook.get(i).getId() + "\t");
//			System.out.print(updatePassbook.get(i).getTransDate()+ "\t");
//			System.out.print(updatePassbook.get(i).getAmount()+ "\t");
//			System.out.print(updatePassbook.get(i).getTransFrom()+ "\t");
//			System.out.print(updatePassbook.get(i).getTransTo()+ "\t");
//			System.out.print(updatePassbook.get(i).getType()+ "\t");
//			System.out.print(updatePassbook.get(i).getClosingBalance()+ "\t");
//			System.out.println();
//		}
//		
		
		PassbookMaintenanceService accountSummaryService =new PassbookMaintenanceServiceImpl();
		String sDate1="2019-08-10";
		String sDate2="2019-10-10";
		Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(sDate1);
		Date date2=new SimpleDateFormat("yyyy-mm-dd").parse(sDate2);
		java.sql.Date startDate= (java.sql.Date) date1;
		java.sql.Date endDate= (java.sql.Date) date2;
		List <Transaction> accountSummary = new ArrayList<Transaction>();
		accountSummary	= accountSummaryService.accountSummary("100202000001",startDate,endDate);
		
		for(int i = 0;i<accountSummary.size();i++)
		{
			System.out.print(accountSummary.get(i).getId() + "\t");
			System.out.print(accountSummary.get(i).getTransDate()+ "\t");
			System.out.print(accountSummary.get(i).getAmount()+ "\t");
			System.out.print(accountSummary.get(i).getTransFrom()+ "\t");
			System.out.print(accountSummary.get(i).getTransTo()+ "\t");
			System.out.print(accountSummary.get(i).getType()+ "\t");
			System.out.print(accountSummary.get(i).getClosingBalance()+ "\t");
			System.out.println();
		}
		
		
//		LoanDisbursalDAO loanDisbursalDAO = new LoanDisbursalDAOImpl();
//		List<Loan> l = new ArrayList<Loan>();
//		try {
//			l = loanDisbursalDAO.retrieveLoanList();
//			loanDisbursalDAO.releaseLoanSheet(l);
//		} catch (IOException | MyException e) {
//			e.printStackTrace();
//		}
//
//
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("-------------Welcome to Pecunia----------------");
//		System.out.println("1. Login");
//		System.out.println("2. Exit");
//		int choice = scanner.nextInt();
//		do
//		{
//			switch(choice)
//			{
//			case 1:
//				//login function
//				break;
//			case 2:
//				System.exit(1);
//				break;
//			default:
//				System.out.println("Incorrect option");
//			}
//		}
//		while(choice != 2);
//		
//	}
//	
//	public boolean login(String email,String password) throws MyException
//	{
//		boolean flag=false,loginSuccess=false;
//		
//		return flag;

	}
}


