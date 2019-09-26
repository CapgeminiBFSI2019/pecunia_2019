package com.capgemini.pecunia.pl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.service.PassbookMaintenanceService;
import com.capgemini.pecunia.service.PassbookMaintenanceServiceImpl;

public class PassbookMain {
	public static void main(String[] args) throws MyException {
		
	
	PassbookMaintenanceService PassbookService=new PassbookMaintenanceServiceImpl();
	List<Transaction> updatePassbook = new ArrayList<Transaction>();
	updatePassbook	= PassbookService.updatePassbook("100202000001");
	
	
	for(int i = 0;i<updatePassbook.size();i++)
	{
		System.out.print(updatePassbook.get(i).getId() + "\t");
		System.out.print(updatePassbook.get(i).getTransDate()+ "\t");
		System.out.print(updatePassbook.get(i).getAmount()+ "\t");
		System.out.print(updatePassbook.get(i).getTransFrom()+ "\t");
		System.out.print(updatePassbook.get(i).getTransTo()+ "\t");
		System.out.print(updatePassbook.get(i).getType()+ "\t");
		System.out.print(updatePassbook.get(i).getClosingBalance()+ "\t");
		System.out.println();
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
//			System.out.print(accountSummary.get(i).getClosingBalance()+ "\t");
//			System.out.println();
//		}
}
}
