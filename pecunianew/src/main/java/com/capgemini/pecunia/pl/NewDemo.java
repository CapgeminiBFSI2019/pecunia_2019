package com.capgemini.pecunia.pl;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;

public class NewDemo {

	public static void main(String[] args) throws MyException, AccountException {

//		LocalDate date = LocalDate.parse("1996-05-01");
//		
////		Account acc = new Account("","","1003","Current","Active",0.00,0.00,null);
////		Customer cust = new Customer("","Nikhil","","123458989876","18264578AQ","9956472628","F", date);
////		Address add = new Address("","123-Manipal Heights","Whitefield","Bangalore","Karnataka","India","560032");
////		
//		//Account acc = new Account("100202000002","","","","",0.00,0.00,null);
		AccountManagementService ams = new AccountManagementServiceImpl();
////		String create = ams.addAccount(cust, add, acc);
////		System.out.println("Account created. Acc ID is: "+ create);
//		
//		Account acc = new Account("100202000001","","","","",0.00,0.00,null);
//		//Customer cust = new Customer("","","","","","8879771134","", date);
//		//Address add = new Address("","123-Sathya Sai Heights","Whitefield","Bangalore","Karnataka","India","560066");
//		//Boolean vali = ams.updateCustomerAddress(acc, add);
//		
//		Boolean vali =ams.deleteAccount(acc);
//		System.out.println(vali);
//
//		Account acc1 = new Account();
//		acc1.setId("10FDGFHJI1");
//		boolean val = ams.validateAccountId(acc1);
//		System.out.println(val);
////	
//		Account acc1 = new Account("","","100","Current","Active",0.00,0.00,null);
//	Customer cust1 = new Customer("","Shanaya","","123458989851","18364578AQ","9956772628","F", null);
//	Address add1 = new Address("","123-Manipal Heights","Whitefield","Bangalore","Karnataka","India","560032");
//	String create = ams.addAccount(cust1, add1, acc1);
//System.out.println("Account created. Acc ID is: "+ create);
	}

}
