package com.capgemini.pecunia.dao;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.MyException;

public interface AccountManagementDAO {
	public boolean deleteAccount(Account acc) throws MyException, AccountException;

	public boolean updateCustomerName(Account acc, Customer cust) throws MyException, AccountException;

	public boolean updateCustomerContact(Account acc, Customer cust) throws MyException, AccountException;

	public boolean updateCustomerAddress(Account acc, Address add) throws MyException, AccountException;

	public String addCustomerDetails(Customer cust, Address add) throws MyException, AccountException;

	public String addAccount(Account acc) throws MyException, AccountException;

	public String calculateAccountId(String id) throws MyException, AccountException;

	public boolean validateAccountId(Account acc) throws MyException, AccountException;

}
