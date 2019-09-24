  package com.capgemini.pecunia.service;


import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.MyException;

public interface AccountManagementService {
	public boolean deleteAccount(String accountId) throws MyException;
	public boolean updateCustomerName(String accountId, Customer cust) throws MyException;
	public boolean updateCustomerContact(String accountId, Customer cust) throws MyException;
	public boolean updateCustomerAddress(String accountId, Address add) throws MyException;
	public String addAccount(Customer cust, Address add, Account acc) throws MyException;
	public String calculateAccountId(Account acc) throws MyException;
}
