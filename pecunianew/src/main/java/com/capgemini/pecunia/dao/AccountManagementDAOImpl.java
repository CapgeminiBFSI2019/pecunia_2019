package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.util.DBConnection;

public class AccountManagementDAOImpl implements AccountManagementDAO {

	@Override
	public boolean deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCustomerName(String accountId, Customer cust) {
		boolean updated = false;
		// Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		return updated;
	}

	@Override
	public boolean updateCustomerContact(String accountId, Customer cust) {
		boolean updated = false;
		// Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		return updated;
	}

	@Override
	public boolean updateCustomerAddress(String accountId, Address add) {
		boolean updated = false;
		// Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
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

		PreparedStatement preparedStatement = null;
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
