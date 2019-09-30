package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Address;
import com.capgemini.pecunia.dto.Customer;
import com.capgemini.pecunia.exception.AccountException;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.exception.PecuniaException;
import com.capgemini.pecunia.util.DBConnection;
import com.capgemini.pecunia.util.LoggerMessage;

public class AccountManagementDAOImpl implements AccountManagementDAO {

	Logger logger = Logger.getRootLogger();

	public AccountManagementDAOImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	
	/*******************************************************************************************************
	 * - Function Name : deleteAccount(Account acc) - Input Parameters : Account
	 * account - Return Type : boolean - Throws : AccountException 
	 * - Author : Rohit Kumar - Creation Date : 24/09/2019 - Description : Deleting an account by
	 * setting account status "Closed" and returns the confirmation to service layer
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/
	
	@Override
	public boolean deleteAccount(Account acc) throws PecuniaException, AccountException {
		boolean isUpdated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;

		int queryResult = 0;

		try {
			preparedStatement = connection.prepareStatement(AccountQueryMapper.DELETE_ACCOUNT);
			preparedStatement.setString(1, acc.getId());
			queryResult = preparedStatement.executeUpdate();

			if (queryResult == 0) {
				logger.error(ErrorConstants.DELETE_ACCOUNT_ERROR);
				throw new AccountException(ErrorConstants.DELETE_ACCOUNT_ERROR);

			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new AccountException(ErrorConstants.DELETE_ACCOUNT_ERROR);
		} finally {
			
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());

					throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
				}
				
			
		}
		isUpdated = true;
		logger.info(LoggerMessage.DELETION_SUCCESSFUL);
		return isUpdated;

	}

	
	/*******************************************************************************************************
	 * - Function Name : updateCustomerName(Account acc, Customer cust) - Input
	 * Parameters : Account acc, Customer cust Return Type : boolean - Throws :
	 * AccountException - Author : Aditi Singh - Creation Date : 24/09/2019 -
	 * Description : Updates customer name in the database and returns confirmation to service layer
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/

	@Override
	public boolean updateCustomerName(Account acc, Customer cust) throws AccountException, PecuniaException {
		boolean isUpdated = false;
		String accId = null;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;

		int queryResult = 0;

		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_CUSTOMER_ID);
			preparedStatement1.setString(1, acc.getId());
			ResultSet resultSet = preparedStatement1.executeQuery();

			if (resultSet.next()) {
				accId = resultSet.getString(1);
			}

			preparedStatement2 = connection.prepareStatement(AccountQueryMapper.UPDATE_NAME);

			preparedStatement2.setString(1, cust.getName());
			preparedStatement2.setString(2, accId);
			queryResult = preparedStatement2.executeUpdate();
			if (queryResult == 0) {
				logger.error(ErrorConstants.UPDATE_ACCOUNT_ERROR);
				throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());

			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		} finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		isUpdated = true;
		logger.info(LoggerMessage.UPDATE_CUSTOMER_NAME_SUCCESSFUL);
		return isUpdated;

	}

	
	/*******************************************************************************************************
	 * - Function Name : updateCustomerContact(Account acc, Customer cust) - Input
	 * Parameters : Account acc, Customer cust Return Type : boolean - Throws :
	 * AccountException - Author : Aditi Singh - Creation Date : 24/09/2019 -
	 * Description : Updates customer contact and returns the confirmation to service layer
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/
	
	@Override
	public boolean updateCustomerContact(Account acc, Customer cust) throws AccountException, PecuniaException {
		boolean isUpdated = false;
		String accId = null;
		Connection connection = null;
		int queryResult = 0;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;

		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_CUSTOMER_ID);
			preparedStatement1.setString(1, acc.getId());

			ResultSet resultSet = preparedStatement1.executeQuery();

			if (resultSet.next()) {
				accId = resultSet.getString(1);
			}

			preparedStatement2 = connection.prepareStatement(AccountQueryMapper.UPDATE_CONTACT);
			preparedStatement2.setString(1, cust.getContact());
			preparedStatement2.setString(2, accId);
			queryResult = preparedStatement2.executeUpdate();
			if (queryResult == 0) {
				logger.error(ErrorConstants.UPDATE_ACCOUNT_ERROR);
				throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());

			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		} finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());

				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		isUpdated = true;
		logger.info(LoggerMessage.UPDATE_CUSTOMER_CONTACT_SUCCESSFUL);
		return isUpdated;
	}

	
	/*******************************************************************************************************
	 * - Function Name : updateCustomerName(Account acc, Address add) - Input
	 * Parameters : Account acc, Address add Return Type : boolean - Throws :
	 * AccountException - Author : Aditi Singh - Creation Date : 24/09/2019 -
	 * Description : Updates customer address and returns the confirmation to service layer
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/
	
	@Override
	public boolean updateCustomerAddress(Account acc, Address add) throws AccountException, PecuniaException {
		boolean isUpdated = false;
		Connection connection = null;
		String accId = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;

		int queryResult = 0;

		try {
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_ADDRESS_ID);
			preparedStatement1.setString(1, acc.getId());
			ResultSet resultSet = preparedStatement1.executeQuery();
			if (resultSet.next()) {
				accId = resultSet.getString(1);
			}

			preparedStatement2 = connection.prepareStatement(AccountQueryMapper.UPDATE_ADDRESS);
			preparedStatement2.setString(1, add.getLine1());
			preparedStatement2.setString(2, add.getLine2());
			preparedStatement2.setString(3, add.getCity());
			preparedStatement2.setString(4, add.getState());
			preparedStatement2.setString(5, add.getCountry());
			preparedStatement2.setString(6, add.getZipcode());
			preparedStatement2.setString(7, accId);
			queryResult = preparedStatement2.executeUpdate();
			if (queryResult == 0) {
				logger.error(ErrorConstants.UPDATE_ACCOUNT_ERROR);
				throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());

			throw new AccountException(ErrorConstants.UPDATE_ACCOUNT_ERROR);
		} finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());

				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		isUpdated = true;
		logger.info(LoggerMessage.UPDATE_CUSTOMER_ADDRESS_SUCCESSFUL);
		return isUpdated;

	}

	
	/*******************************************************************************************************
	 * - Function Name : calculateAccountId(Account acc) - Input Parameters :
	 * Account acc Return Type : String - Throws : AccountException 
	 * - Author : Aditi Singh - Creation Date : 24/09/2019 
	 * - Description : Generation of a new account ID with the given branch ID and type of Account
	 * @throws PecuniaException
	 ********************************************************************************************************/
	
	@Override
	public String calculateAccountId(String id) throws AccountException, PecuniaException {
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long oldId = 0;
		String oldIdstr = null;

		try {
			preparedStatement = connection.prepareStatement(AccountQueryMapper.GET_RECENT_ID);
			preparedStatement.setString(1, id + "%");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				oldIdstr = resultSet.getString(1);
			} else {
				oldIdstr = id + "000000";
			}
			oldId = Long.parseLong(oldIdstr);
			id = Long.toString(oldId + 1);
		} catch (SQLException e) {
			logger.error(e.getMessage());

			throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());

				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		logger.info(LoggerMessage.ACCOUNT_ID_RETURNED);
		return id;
	}

	
	/*******************************************************************************************************
	 * - Function Name : validateAccountId(Account acc) - Input Parameters : Account
	 * account - Return Type : double - Throws : AccountException 
	 * - Author : Aditi Singh - Creation Date : 24/09/2019 
	 * - Description : Validation of Account ID from the database
	 * 
	 * @throws PecuniaException
	 ********************************************************************************************************/
	
	@Override
	public boolean validateAccountId(Account acc) throws PecuniaException, AccountException {

		boolean isValidated = false;
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(AccountQueryMapper.VALIDATE_ID);
			preparedStatement.setString(1, acc.getId());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isValidated = true;
			} else {
				logger.error(ErrorConstants.NO_SUCH_ACCOUNT);
				throw new AccountException(ErrorConstants.NO_SUCH_ACCOUNT);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());

			throw new AccountException(ErrorConstants.ERROR_VALIDATION);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());

				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		logger.info(LoggerMessage.ACCOUNT_VALIDATION_SUCCESSFULL);
		return isValidated;
	}

	
	/*******************************************************************************************************
	 * - Function Name : addAccount(Customer cust, Address add,Account acc) - Input
	 * Parameters : Customer cust, Address add,Account acc - Return Type : String -
	 * Throws : AccountException - Author : Vidushi Razdan - Creation Date :
	 * 24/09/2019 - Description : Addition of new Account by adding address, customer details
	 * and returns the generated customer ID
	 * @throws PecuniaException
	 ********************************************************************************************************/

	
	@Override
	public String addCustomerDetails(Customer cust, Address add) throws PecuniaException, AccountException {
		Connection connection = null;
		String custId = null;
		String addId = null;
		connection = DBConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		int queryResult = 0;

		try {

			preparedStatement = connection.prepareStatement(AccountQueryMapper.ADD_ADDRESS);
			preparedStatement.setString(1, add.getLine1());
			preparedStatement.setString(2, add.getLine2());
			preparedStatement.setString(3, add.getCity());
			preparedStatement.setString(4, add.getState());
			preparedStatement.setString(5, add.getCountry());
			preparedStatement.setString(6, add.getZipcode());
			queryResult = preparedStatement.executeUpdate();

			if (queryResult == 0) {
				logger.error(ErrorConstants.ADD_DETAILS_ERROR);
				throw new AccountException(ErrorConstants.ADD_DETAILS_ERROR);
			}

			logger.info(LoggerMessage.ADD_CUSTOMER_DETAILS_SUCCESSFUL);
			preparedStatement1 = connection.prepareStatement(AccountQueryMapper.GET_RECENT_ADDRESS_ID);

			ResultSet resultSet = preparedStatement1.executeQuery();
			if (resultSet.next()) {
				addId = resultSet.getString(1);
			}

			queryResult = 0;
			PreparedStatement preparedStatement3 = connection.prepareStatement(AccountQueryMapper.ADD_CUSTOMER);
			preparedStatement3.setString(1, cust.getName());
			preparedStatement3.setString(2, addId);
			preparedStatement3.setString(3, cust.getAadhar());
			preparedStatement3.setString(4, cust.getPan());
			preparedStatement3.setString(5, cust.getContact());
			preparedStatement3.setString(6, cust.getGender());
			preparedStatement3.setDate(7, java.sql.Date.valueOf(cust.getDob()));

			queryResult = preparedStatement3.executeUpdate();

			if (queryResult == 0) {
				logger.error(ErrorConstants.ADD_DETAILS_ERROR);
				throw new AccountException(ErrorConstants.ADD_DETAILS_ERROR);
			}

			preparedStatement2 = connection.prepareStatement(AccountQueryMapper.GET_RECENT_CUSTOMER_ID);

			resultSet = preparedStatement2.executeQuery();
			if (resultSet.next()) {
				custId = resultSet.getString(1);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());

			throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());

				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}
		logger.info(LoggerMessage.ADD_CUSTOMER_DETAILS_SUCCESSFUL);
		return custId;

	}

	/*******************************************************************************************************
	 * - Function Name : addAccount(Account acc) - Input Parameters : Account acc - Return Type : String 
	 * - Throws : AccountException - Author : Vidushi Razdan - Creation Date : 24/09/2019 
	 * - Description : Addition of new Account by adding account details and returns the generated accountID
	 * @throws PecuniaException
	 ********************************************************************************************************/
	
	@Override
	public String addAccount(Account acc) throws PecuniaException, AccountException {
		Connection connection = null;
		connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;

		int queryResult = 0;

		try {
			preparedStatement = connection.prepareStatement(AccountQueryMapper.ADD_ACCOUNT);
			preparedStatement.setString(1, acc.getId());
			preparedStatement.setString(2, acc.getHolderId());
			preparedStatement.setString(3, acc.getBranchId());
			preparedStatement.setString(4, acc.getAccountType());
			preparedStatement.setString(5, acc.getStatus());
			preparedStatement.setDouble(6, acc.getBalance());
			preparedStatement.setDouble(7, acc.getInterest());

			queryResult = preparedStatement.executeUpdate();

			if (queryResult == 0) {
				logger.error(ErrorConstants.ADD_DETAILS_ERROR);
				throw new AccountException(ErrorConstants.ADD_DETAILS_ERROR);
			} else {
				logger.info(LoggerMessage.ADD_ACCOUNT_SUCCESSFUL);
				return acc.getId();
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());

			throw new AccountException(ErrorConstants.ACCOUNT_CREATION_ERROR);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());

				throw new AccountException(ErrorConstants.DB_CONNECTION_ERROR);
			}
		}

	}

}
