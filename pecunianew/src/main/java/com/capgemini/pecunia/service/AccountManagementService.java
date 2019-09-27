package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.PecuniaException;

public interface AccountManagementService {
	public boolean deleteAccount(Account acc) throws PecuniaException, AccountException;

	public boolean updateCustomerName(Account acc, Customer cust) throws PecuniaException, AccountException;

	public boolean updateCustomerContact(Account acc, Customer cust) throws PecuniaException, AccountException;

	public boolean updateCustomerAddress(Account acc, Address add) throws PecuniaException, AccountException;

	public String addAccount(Customer cust, Address add, Account acc) throws PecuniaException, AccountException;

	public String calculateAccountId(Account acc) throws PecuniaException, AccountException;

	public boolean validateAccountId(Account acc) throws PecuniaException, AccountException;
}
