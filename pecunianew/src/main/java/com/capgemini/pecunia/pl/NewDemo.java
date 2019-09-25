package com.capgemini.pecunia.pl;

import java.util.Date;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;

public class NewDemo {

	public static void main(String[] args) throws MyException, AccountException {
		
		Date date = new Date();
		Account acc = new Account("100101000001","","","","",0.00,0.00,null);
		Customer cust = new Customer("","Darshana Mehra","","","","","",null);
		AccountManagementService ams = new AccountManagementServiceImpl();
		boolean vali = ams.updateCustomerName(acc, cust);
		System.out.println(vali);
	}

}
