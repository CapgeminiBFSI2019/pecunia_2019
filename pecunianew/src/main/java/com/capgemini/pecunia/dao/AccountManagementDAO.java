package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.MyException;

public interface AccountManagementDAO {
	public boolean deleteAccount(String accountId);
	public boolean updateCustomerName(String accountId, Customer cust) throws MyException;
	public boolean updateCustomerContact(String accountId, Customer cust) throws MyException;
	public boolean updateCustomerAddress(String accountId, Address add) throws MyException;
	public String addAccount(Customer cust, Address add, Account acc) throws MyException;
	public String calculateAccountId(String id) throws MyException;

}
