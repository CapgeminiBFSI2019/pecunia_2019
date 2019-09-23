package com.capgemini.pecunia.service;

import com.capgemini.pecunia.dao.AccountManagementDAO;
import com.capgemini.pecunia.dao.AccountManagementDAOImpl;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;

public class AccountManagementServiceImpl implements AccountManagementService{
	
	AccountManagementDAO accountDAO;

	@Override
	public boolean deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomerName(String accountId, Customer cust) {
		
		/*
		 * Function takes the accountID and the customer object(which contains the updated name)
		 * as arguments, updates the database, and returns a boolean value
		 */
		boolean updated = false;
		accountDAO = new AccountManagementDAOImpl();
		updated = accountDAO.updateCustomerName(accountId, cust);
		return updated;
		
	}

	@Override
	public boolean updateCustomerContact(String accountId, Customer cust) {
		/*
		 * Function takes the accountID and the customer object(which contains the updated name)
		 * as arguments, updates the database, and returns a boolean value
		 */
		boolean updated = false;
		accountDAO = new AccountManagementDAOImpl();
		updated = accountDAO.updateCustomerContact(accountId, cust);
		return updated;
	}

	@Override
	public boolean updateCustomerAddress(String accountId, Address add) {
		/*
		 * Function takes the accountID and the customer object(which contains the updated name)
		 * as arguments, updates the database, and returns a boolean value
		 */
		boolean updated = false;
		accountDAO = new AccountManagementDAOImpl();
		updated = accountDAO.updateCustomerAddress(accountId, add);
		return updated;
	}

	@Override
	public String addAccount(Customer cust, Address add, Account acc) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
