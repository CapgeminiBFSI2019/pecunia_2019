package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.service.AccountManagementService;
import com.capgemini.pecunia.service.AccountManagementServiceImpl;
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
			throw new MyException(ErrorConstants.DELETE_ACCOUNT_ERROR);
		}
		finally
		{
			try
			{
				preparedStatement.close();
			}
			catch(Exception e)
			{
				throw new MyException(ErrorConstants.DELETE_ACCOUNT_ERROR);
			}
		}
		return updated;
	}

	@Override
	public boolean updateCustomerName(Account acc, Customer cust) throws AccountException, MyException {
		boolean updated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement1=null;	
		PreparedStatement preparedStatement2=null;	
		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_CUSTOMER_ID);
			preparedStatement1.setString(1, acc.getId());
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
		
		return updated;
	}

	@Override
	public boolean updateCustomerContact(Account acc, Customer cust) throws AccountException, MyException {
		boolean updated = false;
		Connection connection = null;
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
			int result = preparedStatement2.executeUpdate();
			if(result!=0) {
				updated=true;
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
		
		
		return updated;
	}

	@Override
	public boolean updateCustomerAddress(Account acc, Address add) throws AccountException,MyException {
		boolean updated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();	
		PreparedStatement preparedStatement1=null;
		PreparedStatement preparedStatement2=null;
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
			int result = preparedStatement2.executeUpdate();
			if(result!=0) {
				updated=true;
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
		
		return updated;
	}

	@Override
	public String addAccount(Customer cust, Address add, Account acc) throws AccountException,MyException {
		
		String accountId=null;		
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
			int result = preparedStatement.executeUpdate();
			if(result==0) {
				throw new AccountException("Error adding address");
			}
			else {
				preparedStatement= connection.prepareStatement(AccountQueryMapper.GET_RECENT_ADDRESS_ID);
				ResultSet resultSet = preparedStatement.executeQuery();
				String addId = resultSet.getString(1);
				preparedStatement1= connection.prepareStatement(AccountQueryMapper.ADD_CUSTOMER);
				//preparedStatement1.setString(1,cust.getId());
				preparedStatement1.setString(1,cust.getName());
				preparedStatement1.setString(2,addId);
				preparedStatement1.setString(3,cust.getAadhar());
				preparedStatement1.setString(4,cust.getPan());
				preparedStatement1.setString(5,cust.getContact());
				preparedStatement1.setString(6,cust.getGender());
				preparedStatement1.setDate(7,cust.getDob());
				result = preparedStatement.executeUpdate();
				if(result==0) {
					throw new AccountException("Error adding new customer details");
				}
				else {
					preparedStatement= connection.prepareStatement(AccountQueryMapper.GET_RECENT_CUSTOMER_ID);
					resultSet = preparedStatement.executeQuery();
					String custId = resultSet.getString(1);
					preparedStatement2= connection.prepareStatement(AccountQueryMapper.ADD_ACCOUNT);
					AccountManagementService accMgmt = new AccountManagementServiceImpl();
					accountId = accMgmt.calculateAccountId(acc);
					preparedStatement2.setString(1,accountId);
					preparedStatement2.setString(2,custId);
					preparedStatement2.setString(3,acc.getBranchId());
					preparedStatement2.setString(4,acc.getAccountType());
					preparedStatement2.setString(5,acc.getStatus());
					preparedStatement2.setDouble(6,acc.getBalance());
					preparedStatement2.setDouble(7,acc.getInterest());
					//preparedStatement2.setDate(7,acc.getLastUpdated());
					//dat and timestamp ka isssue has to be sorted, SMH
					result = preparedStatement.executeUpdate();
					if(result==0) {
						throw new AccountException("Error adding new account details");
					}
				}
			}
			
		
	}catch(SQLException e) {
		throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
	}
	finally {
		try {
			preparedStatement.close();
			preparedStatement1.close();
			preparedStatement2.close();
			connection.close();
		} catch (Exception e) {
			throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
		}
	}

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
			throw new AccountException("Error in validating account ID");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		return validated;
	}

}
