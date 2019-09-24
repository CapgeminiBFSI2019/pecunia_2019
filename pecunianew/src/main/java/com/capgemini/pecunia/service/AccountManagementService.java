  package com.capgemini.pecunia.service;


import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.MyException;

public interface AccountManagementService {
	public boolean deleteAccount(String accountId) throws MyException;
	public boolean updateCustomerName(String accountId, Customer cust) throws MyException, AccountException;
	public boolean updateCustomerContact(String accountId, Customer cust) throws MyException, AccountException;
	public boolean updateCustomerAddress(String accountId, Address add) throws MyException,AccountException;
	public String addAccount(Customer cust, Address add, Account acc) throws MyException,AccountException;
	public String calculateAccountId(Account acc) throws MyException,AccountException;
}
