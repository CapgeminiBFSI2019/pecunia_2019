package com.capgemini.pecunia.pl;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.PassbookException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;
import com.capgemini.pecunia.service.PassbookMaintenanceService;
import com.capgemini.pecunia.service.PassbookMaintenanceServiceImpl;

public class PassbookMain {
	public static void main(String[] args) throws MyException, PassbookException, AccountException {
		
	
	PassbookMaintenanceService PassbookService=new PassbookMaintenanceServiceImpl();
	List<Transaction> updatePassbook = new ArrayList<Transaction>();
	String accountId = "nfjkedh77frewf";
	Account account = new Account();
	account.setId(accountId);
	AccountManagementService accountManagementService = new AccountManagementServiceImpl();
	boolean accountExist = accountManagementService.validateAccountId(account);
	if(!accountExist)
	{
		//throw
		System.out.println("invalid account");
	}
	
	updatePassbook	= PassbookService.updatePassbook(accountId);
	
	if(updatePassbook.size() < 1)
	{
		// no trans
		System.out.println("no trans");
	}
	
	else
	{
		for(int i = 0;i<updatePassbook.size();i++)
		{
			System.out.print(updatePassbook.get(i).getId() + "\t");
			System.out.print(updatePassbook.get(i).getTransDate()+ "\t");
			System.out.print(updatePassbook.get(i).getAmount()+ "\t");
			System.out.print(updatePassbook.get(i).getTransFrom()+ "\t");
			System.out.print(updatePassbook.get(i).getTransTo()+ "\t");
			System.out.print(updatePassbook.get(i).getType()+ "\t");
			System.out.print(updatePassbook.get(i).getOption()+ "\t");
			if(updatePassbook.get(i).getOption().equalsIgnoreCase("cheque"))
			{
				System.out.print(updatePassbook.get(i).getChequeId()+"\t");
				
			}
			else {
				System.out.print("-");
			}
			System.out.print(updatePassbook.get(i).getClosingBalance()+ "\t");
			System.out.println();
			}
		}
	}
	
	
	
		
//		PassbookMaintenanceService accountSummaryService =new PassbookMaintenanceServiceImpl();
//		String sDate1="2019-08-10";
//		String sDate2="2019-10-10";
//		LocalDate date1=LocalDate.parse(sDate1);
//		LocalDate date2=LocalDate.parse(sDate2);
//		List <Transaction> accountSummary = new ArrayList<Transaction>();
//		accountSummary	= accountSummaryService.accountSummary("100202000001",date1,date2);
//		
//		for(int i = 0;i<accountSummary.size();i++)
//		{
//			System.out.print(accountSummary.get(i).getId() + "\t");
//			System.out.print(accountSummary.get(i).getTransDate()+ "\t");
//			System.out.print(accountSummary.get(i).getAmount()+ "\t");
//			System.out.print(accountSummary.get(i).getTransFrom()+ "\t");
//			System.out.print(accountSummary.get(i).getTransTo()+ "\t");
//			System.out.print(accountSummary.get(i).getType()+ "\t");
//			System.out.println(updatePassbook.get(i).getOption()+ "\t");
//			System.out.println(updatePassbook.get(i).getChequeId()+"\t");
//			System.out.print(accountSummary.get(i).getClosingBalance()+ "\t");
//			System.out.println();
//		}
}

