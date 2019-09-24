package com.capgemini.pecunia.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.pecunia.dao.AccountManagementDAO;
import com.capgemini.pecunia.dao.AccountManagementDAOImpl;
import com.capgemini.pecunia.dao.AccountQueryMapper;
import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class AccountManagementServiceImpl implements AccountManagementService{
	
	AccountManagementDAO accountDAO;
	@Override
	public boolean deleteAccount(Account acc) throws MyException, AccountException {
		
		boolean updated = false;
		accountDAO = new AccountManagementDAOImpl();
		updated = accountDAO.deleteAccount(acc);
		return updated;
	}

	@Override
	public boolean updateCustomerName(String accountId, Customer cust) throws MyException, AccountException {
		
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
	public boolean updateCustomerContact(String accountId, Customer cust) throws MyException, AccountException {
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
	public boolean updateCustomerAddress(String accountId, Address add) throws MyException, AccountException {
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
	public String addAccount(Customer cust, Address add, Account acc) throws MyException, AccountException {
		
		String accountId = null;
		accountDAO = new AccountManagementDAOImpl();
		accountId= accountDAO.addAccount(cust, add, acc);
		return accountId;

	}

	@Override
	public String calculateAccountId(Account acc) throws MyException, AccountException{
		String id="";
		id = id.concat(acc.getBranchId());
		String type=acc.getAccountType();
		switch(type) {
		case "Savings":
			id = id.concat("01");
			break;
		case "Current":
			id = id.concat("02");
			break;
		case "FD": 
			id = id.concat("03");
			break;
		case "Loan":
			id = id.concat("04");
			break;
		}
		
		accountDAO = new AccountManagementDAOImpl();
		id = accountDAO.calculateAccountId(id);
		return id;
	}
	
	
}
