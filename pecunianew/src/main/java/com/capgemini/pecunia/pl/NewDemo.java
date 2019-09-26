package com.capgemini.pecunia.pl;

import java.util.Date;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;

public class NewDemo {

	public static void main(String[] args) throws MyException, AccountException {
		
		@SuppressWarnings("deprecation")
		Date date = new Date("1996-05-01");
		
		Account acc = new Account("","","1001","Current","Active",0.00,0.00,null);
		Customer cust = new Customer("","Aditi Singh","","123456789876","17264578AQ","9856472628","F",(java.sql.Date) date);
		Address add = new Address("","123-Manipal Heights","Whitefield","Bangalore","Karnataka","India","560032");
		
		AccountManagementService ams = new AccountManagementServiceImpl();
		String vali = ams.addAccount(cust, add, acc);
		System.out.println(vali);
	}

}
