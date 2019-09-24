package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class AccountManagementDAOImpl implements AccountManagementDAO {

	@Override
	public boolean deleteAccount(Account acc) throws MyException {
		boolean updated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement=null;
	
		try
		{
			preparedStatement=connection.prepareStatement(AccountQueryMapper.DELETE_ACCOUNT);
			preparedStatement.setString(1,acc.getId());
			int result = preparedStatement.executeUpdate();
			
			if(result!=0)
			{
				updated = true;
			}
		}
		catch(SQLException e)
		{
			throw new MyException(ErrorConstants.deletAccountError);
		}
		finally
		{
			try
			{
				preparedStatement.close();
			}
			catch(Exception e)
			{
				throw new MyException(ErrorConstants.deletAccountError);
			}
		}
		return updated;
	}

	@Override
	public boolean updateCustomerName(String accountId, Customer cust) throws AccountException, MyException {
		boolean updated = false;

		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement1=null;	
		PreparedStatement preparedStatement2=null;	
		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_CUSTOMER_ID);
			preparedStatement1.setString(1, accountId);
			ResultSet resultSet = preparedStatement1.executeQuery();
			String accId = resultSet.getString(1);
			
			preparedStatement2=connection.prepareStatement(AccountQueryMapper.UPDATE_NAME);
			preparedStatement2.setString(1,cust.getName());
			preparedStatement2.setString(1,accId);
			int result = preparedStatement2.executeUpdate();
			if(result!=0) {
				updated=true;
			}
			
		}catch(SQLException e) {
			throw new AccountException(ErrorConstants.updateAccountError);
		}
		finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (Exception e) {
				//throw new MyException("Error in closing connection");
			}
		}
		
		return updated;
	}

	@Override
	public boolean updateCustomerContact(String accountId, Customer cust) throws AccountException, MyException {
		boolean updated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement1=null;	
		PreparedStatement preparedStatement2=null;	
		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_CUSTOMER_ID);
			preparedStatement1.setString(1, accountId);
			ResultSet resultSet = preparedStatement1.executeQuery();
			String accId = resultSet.getString(1);
			
			preparedStatement2= connection.prepareStatement(AccountQueryMapper.UPDATE_CONTACT);
			preparedStatement2.setString(1,cust.getContact());
			preparedStatement2.setString(1,accId);
			int result = preparedStatement2.executeUpdate();
			if(result!=0) {
				updated=true;
			}
			
		}catch(SQLException e) {
			//throw new MyException(ErrorConstants.techError);
		}
		finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (Exception e) {
				throw new AccountException(ErrorConstants.updateAccountError);
			}
		}
		
		
		return updated;
	}

	@Override
	public boolean updateCustomerAddress(String accountId, Address add) throws AccountException,MyException {
		boolean updated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement1=null;
		PreparedStatement preparedStatement2=null;
		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_ADDRESS_ID);
			preparedStatement1.setString(1, accountId);
			ResultSet resultSet = preparedStatement1.executeQuery();
			String accId = resultSet.getString(1);
			
			preparedStatement2= connection.prepareStatement(AccountQueryMapper.UPDATE_ADDRESS);
			preparedStatement2.setString(1,add.getLine1());
			preparedStatement2.setString(2,add.getLine2());
			preparedStatement2.setString(3,add.getCity());
			preparedStatement2.setString(4,add.getState());
			preparedStatement2.setString(5,add.getCountry());
			preparedStatement2.setString(6,add.getZipcode());
			preparedStatement2.setString(7,accId);
			int result = preparedStatement2.executeUpdate();
			if(result!=0) {
				updated=true;
			}
			
		}catch(SQLException e) {
	//has to 
		}
		finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (Exception e) {
				throw new AccountException(ErrorConstants.updateAccountError);
			}
		}
		
		return updated;
	}

	@Override
	public String addAccount(Customer cust, Address add, Account acc) throws AccountException,MyException {
		
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		try {
			
			preparedStatement= connection.prepareStatement(AccountQueryMapper.ADD_ADDRESS);
			preparedStatement.setString(1,add.getLine1());
			preparedStatement.setString(2,add.getLine2());
			preparedStatement.setString(3,add.getCity());
			preparedStatement.setString(4,add.getState());
			preparedStatement.setString(5,add.getCountry());
			preparedStatement.setString(6,add.getZipcode());
			
			preparedStatement1= connection.prepareStatement(AccountQueryMapper.ADD_CUSTOMER);
			preparedStatement1.setString(1,cust.getId());
			preparedStatement1.setString(2,cust.getName());
			preparedStatement1.setString(3,cust.getAddressId());
			preparedStatement1.setString(4,cust.getAadhar());
			preparedStatement1.setString(5,cust.getPan());
			preparedStatement1.setString(6,cust.getContact());
			preparedStatement1.setString(7,cust.getGender());
			preparedStatement1.setDate(8,cust.getDob());
			
			preparedStatement2= connection.prepareStatement(AccountQueryMapper.ADD_ACCOUNT);
			preparedStatement2.setString(1,acc.getHolderId());
			preparedStatement2.setString(2,acc.getBranchId());
			preparedStatement2.setString(3,acc.getAccountType());
			preparedStatement2.setString(4,acc.getStatus());
			preparedStatement2.setDouble(5,acc.getBalance());
			preparedStatement2.setDouble(6,acc.getInterest());
			//preparedStatement2.setDate(7,acc.getLastUpdated());
			//dat and timestamp ka isssue has to be sorted, SMH
			

		
	}catch(SQLException e) {
		//throw new MyException(ErrorConstants.techError);
	}
	finally {
		try {
			preparedStatement.close();
			preparedStatement1.close();
			preparedStatement2.close();
			connection.close();
		} catch (Exception e) {
			throw new AccountException(ErrorConstants.accountCreationError);
		}
	}
	
		
		
		//ResultSet resultSet = null;

		String accountId = null;

		return accountId;

	}

	@Override
	public String calculateAccountId(String id) throws AccountException, MyException {
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement=null;		

		ResultSet resultSet = null;
		long oldId = 0;
		try {
			preparedStatement = connection.prepareStatement(AccountQueryMapper.GET_RECENT_ID);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			oldId = Long.parseLong(resultSet.getString(1));
			id = Long.toString(oldId + 1);
		} catch (SQLException sqlException) {// CATCH EXCEPTION

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// HANDLE EXCEPTION
			}
		}
		return id;
	}

}
