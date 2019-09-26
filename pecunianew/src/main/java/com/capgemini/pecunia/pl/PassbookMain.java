package com.capgemini.pecunia.pl;

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
}
}
