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
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.Constants;
import com.capgemini.pecunia.util.DBConnection;

public class AccountManagementServiceImpl implements AccountManagementService{
	
	AccountManagementDAO accountDAO;
	@Override
	public boolean deleteAccount(Account acc) throws MyException, AccountException {
		
		boolean updated = false;
		boolean validated = validateAccountId(acc);
		if(validated) {
		accountDAO = new AccountManagementDAOImpl();
		updated = accountDAO.deleteAccount(acc);
		}
		else {
			throw new AccountException("Account ID doesn't exist");
		}
		return updated;
	}

	@Override
	public boolean updateCustomerName(Account acc, Customer cust) throws MyException, AccountException {
		
		/*
		 * Function takes the accountID and the customer object(which contains the updated name)
		 * as arguments, updates the database, and returns a boolean value
		 */
		boolean updated = false;
		boolean validated = validateAccountId(acc);
		if(validated) {
			accountDAO = new AccountManagementDAOImpl();
			updated = accountDAO.updateCustomerName(acc, cust);
		}
		else {
			throw new AccountException("Account ID doesn't exist");
		}
		return updated;
		
	}

	@Override
	public boolean updateCustomerContact(Account acc, Customer cust) throws MyException, AccountException {
		/*
		 * Function takes the accountID and the customer object(which contains the updated name)
		 * as arguments, updates the database, and returns a boolean value
		 */
		boolean updated = false;
		boolean validated = validateAccountId(acc);
		if(validated) {
		accountDAO = new AccountManagementDAOImpl();
		updated = accountDAO.updateCustomerContact(acc, cust);
		}
		else {
			throw new AccountException("Account ID doesn't exist");
		}
		return updated;
	}

	@Override
	public boolean updateCustomerAddress(Account acc, Address add) throws MyException, AccountException {
		/*
		 * Function takes the accountID and the customer object(which contains the updated name)
		 * as arguments, updates the database, and returns a boolean value
		 */
		boolean updated = false;
		boolean validated = validateAccountId(acc);
		if(validated) {
		accountDAO = new AccountManagementDAOImpl();
		updated = accountDAO.updateCustomerAddress(acc, add);
		}
		else {
		throw new AccountException("Account ID doesn't exist");
		}
		return updated;
	}

	@Override
	public String calculateAccountId(Account acc) throws MyException, AccountException{
		String id="";
		id = id.concat(acc.getBranchId());
		String type=acc.getAccountType();
		switch(type) {
		case Constants.SAVINGS:
			id = id.concat(Constants.CODE_SAVINGS);
			break;
		case Constants.CURRENT:
			id = id.concat(Constants.CODE_CURRENT);
			break;
		case Constants.FD: 
			id = id.concat(Constants.CODE_FD);
			break;
		case Constants.LOAN:
			id = id.concat(Constants.CODE_LOAN);
			break;
		}
		
		accountDAO = new AccountManagementDAOImpl();
		id = accountDAO.calculateAccountId(id);
		return id;
	}

	@Override
	public boolean validateAccountId(Account acc) throws MyException, AccountException {
		boolean validated=false;
		validated = accountDAO.validateAccountId(acc);
		return validated;
	}

	

	
	
	
	@Override
	public String addAccount(Customer cust, Address add,Account acc) throws MyException, AccountException {
		accountDAO = new AccountManagementDAOImpl();
		String custId= accountDAO.addCustomerDetails(cust, add);
		acc.setHolderId(custId);
		String accountId = calculateAccountId(acc);
		acc.setId(accountId);
		boolean created = accountDAO.addAccount(acc);
		if(!created) {
			throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
		}
		return accountId;
	}
	
	
}
