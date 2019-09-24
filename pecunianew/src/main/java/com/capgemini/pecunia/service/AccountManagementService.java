  package com.capgemini.pecunia.service;


import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.MyException;

public interface AccountManagementService {
	public boolean deleteAccount(String accountId);
	public boolean updateCustomerName(String accountId, Customer cust);
	public boolean updateCustomerContact(String accountId, Customer cust);
	public boolean updateCustomerAddress(String accountId, Address add);
	public String addAccount(Customer cust, Address add, Account acc);
	public String calculateAccountId(Account acc);
}