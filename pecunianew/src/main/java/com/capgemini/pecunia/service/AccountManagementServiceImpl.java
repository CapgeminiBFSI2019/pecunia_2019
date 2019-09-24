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
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

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
		
	String accountId = null;
		accountDAO = new AccountManagementDAOImpl();
		accountId= accountDAO.addAccount(cust, add, acc);
		return accountId;

	}

	@Override
	public String calculateAccountId(Account acc){
		String id="";
		id.concat(acc.getBranchId());
		String type=acc.getAccountType();
		switch(type) {
		case "Savings":
			id.concat("01");
			break;
		case "Current":
			id.concat("02");
			break;
		case "FD": 
			id.concat("03");
			break;
		case "Loan":
			id.concat("04");
			break;
		}
		
		Connection connection = null;
		try {
			connection = DBConnection.getInstance().getConnection();
		} catch (MyException e) {
			//HANDLE EXCEPTION
		}	
		
		PreparedStatement preparedStatement=null;		
		ResultSet resultSet = null;
		long oldId = 0;
		try
		{		
			preparedStatement=connection.prepareStatement(AccountQueryMapper.GET_RECENT_ID);
			preparedStatement.setString(1,id);
			resultSet = preparedStatement.executeQuery();
			oldId = Long.parseLong(resultSet.getString(1));
			id = Long.toString(oldId+1);
		}
		catch(SQLException sqlException)
		{//CATCH EXCEPTION
			
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// HANDLE EXCEPTION
			}
		}
		
		return id;
	}
	
	
}
