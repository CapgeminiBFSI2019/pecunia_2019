package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.pecunia.dto.Account;

import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.ErrorConstants;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

	Logger logger = Logger.getRootLogger();

	public TransactionDAOImpl() {
		PropertyConfigurator.configure("resources//log4j.properties");
	}

	/*******************************************************************************************************
	 * - Function Name : getBalance(Account account) 
	 * - Input Parameters : account object
	 * - Return Type : double 
	 * - Throws : TransactionException,MyException
	 * - Author : Rohan Patil
	 * - Creation Date : 23/09/2019 
	 * - Description : Getting balance of the specified account
	 ********************************************************************************************************/
	
	@Override
	public double getBalance(Account account) throws MyException, TransactionException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String accountId = account.getId();
		double balance = -1;

		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.GET_ACCOUNT_BALANCE_QUERY);

			preparedStatement.setString(1, accountId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				balance = resultSet.getDouble("balance");
			}

			if (balance == -1) {
				throw new TransactionException("Balance retrieve fail");
			}

		} catch (TransactionException me) {
			// logger here
			System.out.println("trans:"+me.getMessage());
			logger.error("");
			throw new TransactionException(me.getMessage());
		} catch (Exception e) {
			// add logger here
			System.out.println("exc:"+e.getMessage());
			logger.error("");
			throw new MyException(e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				logger.error("Error in closing db connection");
				throw new MyException("Error in closing db connection");
			}

		}
		return balance;
	}
	
	/*******************************************************************************************************
	 * - Function Name : updateBalance(Account account) 
	 * - Input Parameters : account object
	 * - Return Type : boolean 
	 * - Throws : TransactionException,MyException
	 * - Author : Anwesha Das
	 * - Creation Date : 23/09/2019 
	 * - Description : update balance of the specified account
	 ********************************************************************************************************/
	
	@Override
	public boolean updateBalance(Account account) throws MyException, TransactionException {
		boolean flag = false;
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		String accountId = account.getId();
		int rowsAffected = 0;
		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.UPDATE_ACOCUNT_BALANCE_QUERY);
			preparedStatement.setDouble(1, account.getBalance());
			preparedStatement.setString(2, accountId);
			rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0) {
				flag = true;
			} else {
				// logger here
				logger.error("Update balance failed");
				throw new TransactionException("Update balance failed");
			}
		} catch (TransactionException te) {
			// logger here
			throw new TransactionException(te.getMessage());
		} catch (Exception e) {
			// logger here
			throw new MyException(e.getMessage());
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// logger here
				logger.error("Error closing db connection");
				throw new MyException("Error closing db connection");
			}
		}
		return flag;
	}

	/*******************************************************************************************************
	 * - Function Name : generateChequeId(Cheque cheque)
	 * - Input Parameters : cheque object
	 * - Return Type : int 
	 * - Throws : TransactionException,MyException
	 * - Author : Anish Basu
	 * - Creation Date : 23/09/2019 
	 * - Description : generate cheque id of the specified account
	 ********************************************************************************************************/
	
	@Override
	public int generateChequeId(Cheque cheque) throws MyException, TransactionException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		int chequeId = 0;
		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.INSERT_CHEQUE_QUERY,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, cheque.getNum());
			preparedStatement.setString(2, cheque.getAccountNo());
			preparedStatement.setString(3, cheque.getHolderName());
			preparedStatement.setString(4, cheque.getBankName());
			preparedStatement.setString(5, cheque.getIfsc());
			preparedStatement.setDate(6, java.sql.Date.valueOf(cheque.getIssueDate()));
			preparedStatement.setString(7, cheque.getStatus());
			
			try
			{
				preparedStatement.executeUpdate();
			}
			catch(SQLException e)
			{
				throw new MyException(e.getMessage());
			}
			 resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				chequeId = resultSet.getInt(1);
			} else {
				throw new TransactionException("Error occured during cheque insertion");
			}
		} catch (TransactionException e) {
			// TODO logger here
			logger.error("");
			throw new TransactionException(e.getMessage());
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO logger here
				logger.error("");
				throw new MyException(e.getMessage());
			}

		}
		return chequeId;
	}

	/*******************************************************************************************************
	 * - Function Name : generateTransactionId(Transaction transaction)
	 * - Input Parameters : transaction object
	 * - Return Type : int 
	 * - Throws : TransactionException,MyException
	 * - Author : Arpan Mondal
	 * - Creation Date : 23/09/2019 
	 * - Description : generate transaction id of the specified account
	 ********************************************************************************************************/
	
	@Override
	public int generateTransactionId(Transaction transaction) throws MyException, TransactionException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		int transId = 0;

		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.INSERT_TRANSACTION_QUERY,Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, transaction.getAccountId());
			preparedStatement.setString(2, transaction.getType());
			preparedStatement.setDouble(3, transaction.getAmount());
			preparedStatement.setString(4, transaction.getOption());
			preparedStatement.setInt(5, transaction.getChequeId());
			preparedStatement.setString(6, transaction.getTransFrom());
			preparedStatement.setString(7, transaction.getTransTo());
			preparedStatement.setDouble(8, transaction.getClosingBalance());
			try
			{
				preparedStatement.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				transId = resultSet.getInt(1);
			} else {
				logger.error("Error occured during transaction insertion");
				throw new TransactionException("Error occured during transaction insertion");
			}
		} catch (TransactionException e) {
			// TODO logger here
			logger.error("");
			throw new TransactionException(e.getMessage());
		} catch (Exception e) {
			throw new MyException(e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO logger here
				logger.error("");
				throw new MyException(e.getMessage());
			}

		}

		return transId;
	}

}
