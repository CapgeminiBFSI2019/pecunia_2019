package com.capgemini.pecunia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.capgemini.pecunia.dto.Account;
import com.capgemini.pecunia.dto.Cheque;
import com.capgemini.pecunia.dto.Transaction;
import com.capgemini.pecunia.exception.MyException;
import com.capgemini.pecunia.exception.TransactionException;
import com.capgemini.pecunia.util.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public double getbalance(Account account) throws MyException, TransactionException {
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
			throw new TransactionException(me.getMessage());
		} catch (Exception e) {
			// add logger here
			throw new MyException(e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				throw new MyException("Error in closing db connection");
			}

		}
		return balance;
	}

	@Override
	public boolean updateBalance(Account account) throws MyException, TransactionException {
		boolean flag = false;
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		String accountId = account.getId();
		int rowsAffected = 0;
		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.UPDATE_ACOCUNT_BALANCE_QUERY);
			preparedStatement.setString(1, accountId);
			rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected != 0) {
				flag = true;
			} else {
				// logger here
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
				throw new MyException("Error closing db connection");
			}
		}
		return flag;
	}
	
	
	public int debitusingCheque(Transaction transaction, Cheque cheque) {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String accId=transaction.getAccountId();
		double newBalance=transaction.getClosingBalance();
		Date transDate=transaction.getTransDate();
		java.sql.Date sqltransactionDate = new java.sql.Date(transDate.getTime());
		Account acc=new Account(accId, Values.NA, Values.NA, Values.NA, Values.NA, newBalance, Value.NA, Value.NA);
		try {
        boolean flag=updateBalance(acc);
		if(flag) {
			//Cheque clearedCheque=new Cheque(Values.NA, cheque.getNum(), accId, cheque.getHolderName(), Value.BANK_NAME, cheque.getIfsc(), cheque.getIssueDate(), Values.CHEQUE_STATUS_CLEARED);
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.INSERT_CHEQUE_QUERY);
			preparedStatement.setInt(1,cheque.getNum());
			preparedStatement.setString(2,accId);
			preparedStatement.setString(3,cheque.getHolderName());
			preparedStatement.setString(4,Values.BANK_NAME);
			preparedStatement.setString(5,cheque.getIfsc());
			preparedStatement.setDate(6, sqltransactionDate);
			preparedStatement.setString(7, Values.CHEQUE_STATUS_CLEARED);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.GET_CHEQUE_ID_QUERY);
			preparedStatement.setString(1, accId);
			resultSet = preparedStatement.executeQuery();
			String chequeId=resultSet.getString("cheque_id");
		}
		else {
			
		}
		return 0;
	}finally {
		try {
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// logger here
			throw new MyException("Error closing db connection");
		}
	}


}

	@Override
	public int generateChequeId(Cheque cheque) throws MyException, TransactionException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		int chequeId = 0;
		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.INSERT_CHEQUE_QUERY);

			preparedStatement.setInt(1, cheque.getNum());
			preparedStatement.setString(2, cheque.getAccountNo());
			preparedStatement.setString(3, cheque.getHolderName());
			preparedStatement.setString(4, cheque.getBankName());
			preparedStatement.setString(5, cheque.getIfsc());
			preparedStatement.setDate(6, (Date) cheque.getIssueDate());
			preparedStatement.setString(7, cheque.getStatus());

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				chequeId = resultSet.getInt(1);
			} else {
				throw new TransactionException("Error occured during cheque insertion");
			}
		} catch (TransactionException e) {
			// TODO logger here
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
				throw new MyException(e.getMessage());
			}

		}
		return chequeId;
	}

	@Override
	public int generateTransactionId(Transaction transaction) throws MyException, TransactionException {
		Connection connection = DBConnection.getInstance().getConnection();

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		int transId = 0;

		try {
			preparedStatement = connection.prepareStatement(TransactionQueryMapper.INSERT_TRANSACTION_QUERY);

			preparedStatement.setString(1, transaction.getAccountId());
			preparedStatement.setString(2, transaction.getType());
			preparedStatement.setDouble(3, transaction.getAmount());
			preparedStatement.setString(4, transaction.getOption());
			preparedStatement.setInt(5, transaction.getChequeId());
			preparedStatement.setString(6, transaction.getTransFrom());
			preparedStatement.setString(7, transaction.getTransTo());
			preparedStatement.setDouble(8, transaction.getClosingBalance());

			resultSet = preparedStatement.getGeneratedKeys();

			if (resultSet.next()) {
				transId = resultSet.getInt(1);
			} else {
				throw new TransactionException("Error occured during transaction insertion");
			}
		} catch (TransactionException e) {
			// TODO logger here
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
				throw new MyException(e.getMessage());
			}

		}

		return transId;
	}


}