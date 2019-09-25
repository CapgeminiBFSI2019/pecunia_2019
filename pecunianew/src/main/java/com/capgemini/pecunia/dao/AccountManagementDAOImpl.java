package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class AccountManagementDAOImpl implements AccountManagementDAO {
	
	
	Logger logger=Logger.getRootLogger();
	public AccountManagementDAOImpl()
	{
	PropertyConfigurator.configure("resources//log4j.properties");
	
	}

	@Override
	public boolean deleteAccount(Account acc) throws MyException, AccountException {
		boolean updated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement=null;
	
		int queryResult=0;
		
		try
		{
			preparedStatement=connection.prepareStatement(AccountQueryMapper.DELETE_ACCOUNT);
			preparedStatement.setString(1,acc.getId());
			queryResult=preparedStatement.executeUpdate();
			
			
			if(queryResult==0)
			{
				logger.error("Deletion failed ");
				throw new AccountException(ErrorConstants.DELETE_ACCOUNT_ERROR);

			}
			
		}
		catch(SQLException e)
		{
			throw new AccountException(ErrorConstants.DELETE_ACCOUNT_ERROR);
		}
		finally
		{
			try
			{
				preparedStatement.close();
				connection.close();
			}
			catch(Exception e)
			{
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		updated = true;
		logger.info("Deletion succesful");
		return updated;
		
	}

	@Override
	public boolean updateCustomerName(Account acc, Customer cust) throws AccountException, MyException {
		boolean updated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement1=null;	
		PreparedStatement preparedStatement2=null;	
		
		int queryResult=0;
		
		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_CUSTOMER_ID);
			preparedStatement1.setString(1, acc.getId());
			ResultSet resultSet = preparedStatement1.executeQuery();
			
			String accId = resultSet.getString(1);
			
	
			preparedStatement2=connection.prepareStatement(AccountQueryMapper.UPDATE_NAME);
			
			preparedStatement2.setString(1,cust.getName());
			preparedStatement2.setString(1,accId);
			queryResult = preparedStatement2.executeUpdate();						
			if(queryResult==0)
			{
				logger.error("Error in updating customer details ");
				throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
		}catch(SQLException e) {
			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
		finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (Exception e) {
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		updated = true;
		logger.info("Customer Details added");
		return updated;
		
	}

	@Override
	public boolean updateCustomerContact(Account acc, Customer cust) throws AccountException, MyException {
		boolean updated = false;
		Connection connection = null;
		int queryResult=0;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement1=null;	
		PreparedStatement preparedStatement2=null;	
		
		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_CUSTOMER_ID);
			preparedStatement1.setString(1, acc.getId());
			
			ResultSet resultSet = preparedStatement1.executeQuery();
			String accId = resultSet.getString(1);
			
			preparedStatement2= connection.prepareStatement(AccountQueryMapper.UPDATE_CONTACT);
			preparedStatement2.setString(1,cust.getContact());
			preparedStatement2.setString(1,accId);
			queryResult = preparedStatement2.executeUpdate();			
			if(queryResult==0)
			{
				logger.error("Error in updating customer contact.");
				throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
		}catch(SQLException e) {
			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
		finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (Exception e) {
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		updated = true;
		logger.info("Customer Contact updated.");
		return updated;
	}

	@Override
	public boolean updateCustomerAddress(Account acc, Address add) throws AccountException,MyException {
		boolean updated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement1=null;
		PreparedStatement preparedStatement2=null;
		
		int queryResult=0;
		
		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_ADDRESS_ID);
			preparedStatement1.setString(1, acc.getId());
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
			queryResult = preparedStatement2.executeUpdate();
			if(queryResult==0)
			{
				logger.error("Error in updating customer address.");
				throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
			
		}catch(SQLException e) {
			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		}
		finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (Exception e) {
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		updated = true;
		logger.info("Customer Address Details updated.");
		return updated;
		
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
		} catch (SQLException e) {
			throw new AccountException("Error in creating account ID");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		return id;
	}

	@Override
	public boolean validateAccountId(Account acc) throws MyException, AccountException {
		boolean validated=false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement=null;		

		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement(AccountQueryMapper.VALIDATE_ID);
			preparedStatement.setString(1, acc.getId());
			resultSet = preparedStatement.executeQuery();
			if(resultSet!=null) {
				validated=true;
			}
		}catch(SQLException e) {
			throw new AccountException(ErrorConstants.ERROR_VALIDATION);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		return validated;
	}

	
	
	@Override
	public String addCustomerDetails(Customer cust, Address add) throws MyException, AccountException {
		Connection connection = null;
		String custId=null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		int queryResult=0;
		
		try {
			
			preparedStatement= connection.prepareStatement(AccountQueryMapper.ADD_ADDRESS);
			preparedStatement.setString(1,add.getLine1());
			preparedStatement.setString(2,add.getLine2());
			preparedStatement.setString(3,add.getCity());
			preparedStatement.setString(4,add.getState());
			preparedStatement.setString(5,add.getCountry());
			preparedStatement.setString(6,add.getZipcode());
			queryResult = preparedStatement.executeUpdate();
			
			if(queryResult==0)
			{
				logger.error("Error in adding customer address.");
				throw new AccountException(ErrorConstants.ADD_DETAILS_ERROR);
			}

			    logger.info("Customer Address Details added.");
				preparedStatement1= connection.prepareStatement(AccountQueryMapper.GET_RECENT_ADDRESS_ID);
				ResultSet resultSet = preparedStatement1.executeQuery();
				String addId = resultSet.getString(1);
				preparedStatement1= connection.prepareStatement(AccountQueryMapper.ADD_CUSTOMER);
				preparedStatement1.setString(1,cust.getName());
				preparedStatement1.setString(2,addId);
				preparedStatement1.setString(3,cust.getAadhar());
				preparedStatement1.setString(4,cust.getPan());
				preparedStatement1.setString(5,cust.getContact());
				preparedStatement1.setString(6,cust.getGender());
				preparedStatement1.setDate(7,cust.getDob());
				queryResult = preparedStatement1.executeUpdate();
				if(queryResult==1)
				{
					logger.error("Error in adding customer details.");
					throw new AccountException(ErrorConstants.ADD_DETAILS_ERROR);
				}
			
			preparedStatement2= connection.prepareStatement(AccountQueryMapper.GET_RECENT_CUSTOMER_ID);
			resultSet = preparedStatement2.executeQuery();
			custId = resultSet.getString(1);	
			
				
	}catch(SQLException e) {
		throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
	}finally {
		    try {
			connection.close();
		      } catch (Exception e) {
			throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
		     }
	}
		logger.info("Customer Details added.");
		return custId;
		
	
	}
	
	@Override
	public String addAccount(Account acc) throws MyException, AccountException {
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		Date date = new Date();
		acc.setLastUpdated(date);
		PreparedStatement preparedStatement = null;
		
		int queryResult=0;
		
		try {
			preparedStatement= connection.prepareStatement(AccountQueryMapper.ADD_ACCOUNT);
			preparedStatement.setString(1,acc.getId());
			preparedStatement.setString(2,acc.getHolderId());
			preparedStatement.setString(3,acc.getBranchId());
			preparedStatement.setString(4,acc.getAccountType());
			preparedStatement.setString(5,acc.getStatus());
			preparedStatement.setDouble(6,acc.getBalance());
			preparedStatement.setDouble(7,acc.getInterest());
			preparedStatement.setDate(8,(java.sql.Date) acc.getLastUpdated());
			queryResult = preparedStatement.executeUpdate();
			if(queryResult==0) {
				logger.error("Error in adding customer details.");
				throw new AccountException(ErrorConstants.ADD_DETAILS_ERROR);
			}
			else
			{
				logger.info("New Account added.");
				return acc.getId();
			}
			
		}catch(SQLException e) {
			throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		
	}

}
