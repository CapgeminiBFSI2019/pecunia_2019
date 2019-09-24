package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class AccountManagementDAOImpl implements AccountManagementDAO {

	@Override
	public boolean deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomerName(String accountId, Customer cust) throws MyException {
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
			throw new MyException(ErrorConstants.techError);
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
	public boolean updateCustomerContact(String accountId, Customer cust) throws MyException {
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
				throw new MyException("Error in closing connection");
			}
		}
		
		
		return updated;
	}

	@Override
	public boolean updateCustomerAddress(String accountId, Address add) throws MyException {
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
			//throw new MyException(ErrorConstants.techError);
		}
		finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (Exception e) {
				throw new MyException("Error in closing connection");
			}
		}
		
		return updated;
	}

	@Override
	public String addAccount(Customer cust, Address add, Account acc) {
		// gotta add Dbconnection
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String accountId = null;

		return accountId;

		// return null;
	}

	@Override
	public String calculateAccountId(String id) throws MyException {
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
